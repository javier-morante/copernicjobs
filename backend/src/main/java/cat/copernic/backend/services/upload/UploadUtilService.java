package cat.copernic.backend.services.upload;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import cat.copernic.backend.services.upload.exceptions.FileMistmatchTypeException;
import cat.copernic.backend.services.upload.lambda.UpLoadFilter;
import lombok.Data;

@Service
@Data
public class UploadUtilService {

    private final Logger logger = LoggerFactory.getLogger(UploadUtilService.class);

    private String apiPath;

    private String fileUrl;

    private String savePath;

    private String type;

    private UpLoadFilter filter;

    public UploadUtilService() {
        this.savePath = "";
    }

    // In this method save a file checkking file type and returning a code refering file
    public String save(MultipartFile file) throws IOException, FileMistmatchTypeException {

        System.out.println("File => " + file.getName());

        if (!this.isType(file)) {
            throw new FileMistmatchTypeException("El arcxiu no es "+type);
        }

        File directory = new File(savePath);
        System.out.println("Dir Path => " + directory.getAbsolutePath());
        if (!directory.exists()) {
            directory.mkdirs();
        }

        byte[] bytes = file.getBytes();

        String fileCode = RandomStringUtils.randomAlphanumeric(8);

        String fileName = fileCode + "-" + file.getOriginalFilename();

        this.transformToURL(fileName);

        Path path = Paths.get(savePath + File.separator + fileName);
        Files.write(path, bytes);
        
        return fileCode;
        
    }

    private void transformToURL(String fileName){
        String fileURL = null;
        if(apiPath != null){
            fileURL = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path(apiPath)
                    .path(fileName)
                    .toUriString();
        }
        this.fileUrl = fileURL;
    }

    public boolean isType(MultipartFile file){
        return filter.filter(file);
    }

    public void fileDelete(String oldPdfCode){
        Path dirPath = Paths.get(savePath);
        try {
            Files.list(dirPath).forEach(file -> {
                if (file.getFileName().toString().startsWith(oldPdfCode)) {
                    try {
                        Files.deleteIfExists(file);
                    } catch (IOException e) { 
                        logger.error(e.toString());
                    }
                    return;
                }
            });
        } catch (IOException e) {
            logger.error(e.toString());
        }
    }
}
