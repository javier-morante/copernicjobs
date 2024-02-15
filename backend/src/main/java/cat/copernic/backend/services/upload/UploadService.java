package cat.copernic.backend.services.upload;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import cat.copernic.backend.data.models.student.StudentService;
import cat.copernic.backend.data.models.student_professional_profile.StudentProfessionalProfileService;
import cat.copernic.backend.services.upload.exceptions.FileMistmatchTypeException;
import cat.copernic.backend.services.upload.exceptions.FileTypeNotExistException;
import cat.copernic.backend.services.utils.validators.profesional_profile.ValidateProfesionalProfile;
import lombok.Data;

@Service
@Data
public class UploadService {

    String[][] imageType = {
        {"image/png", ".png"},
        {"image/jpeg", ".jpeg"},
        {"image/jpeg", ".jpg"},
        {"image/jpeg", ".jiff"},
    };
    

    @Autowired
    StudentService studentService;

    @Autowired
    StudentProfessionalProfileService studentPPService;

    @Value("${upload.pdf.dir}")
    private String pdfpath;

    @Value("${upload.images.dir}")
    private String imagepath;
    
    @Autowired
    UploadUtilService uploadUtilService;

    @Autowired
    ValidateProfesionalProfile vProfesionalProfile;

    public String save(String fileType,String oldCode, MultipartFile file) throws FileTypeNotExistException, IOException, FileMistmatchTypeException{

        switch (fileType) {
            case "pdf":
                uploadUtilService.setSavePath(pdfpath);
                if (!this.vProfesionalProfile.isEmpty(oldCode)) {
                    this.uploadUtilService.fileDelete(oldCode);
                }
                uploadUtilService.setFilter((n)->{
                    return n.getContentType().equalsIgnoreCase("aplication/pdf") ||
                           n.getOriginalFilename().toLowerCase().endsWith(".pdf");
                });
                break;
            case "image":
                if (oldCode != null?!this.vProfesionalProfile.isEmpty(oldCode):false) {
                    String code = this.urlTransform(oldCode);
                    if(this.vProfesionalProfile.validateCode(oldCode)) this.uploadUtilService.fileDelete(code);
                }
                uploadUtilService.setApiPath("/api/images/");
                uploadUtilService.setSavePath(imagepath);
                uploadUtilService.setFilter((n)->{
                    for (String[] types : imageType) {
                        if(n.getContentType().equalsIgnoreCase(types[0]) ||
                           n.getOriginalFilename().toLowerCase().endsWith(types[1])){
                            return true;
                        }
                    }

                    return false;
                });
                break;
            default:
                throw new FileTypeNotExistException("El tipus d'archivo no existeix");
        }

        return this.uploadUtilService.save(file);
    }

    public String urlTransform(String str){

        String[] firstStage = str.split("/");
        String secondStage = firstStage[firstStage.length -1 ].split("-")[0];
        System.out.println("\n\n"+secondStage);
        return secondStage;

    }   

    public String getFileUrl(){
        return this.uploadUtilService.getFileUrl();
    }
}
