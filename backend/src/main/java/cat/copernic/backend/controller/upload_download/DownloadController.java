package cat.copernic.backend.controller.upload_download;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cat.copernic.backend.services.download.DownloadService;


@RestController
@RequestMapping("/api/download")
public class DownloadController {

    @Autowired
    DownloadService downloadService;
    

    // On this method take a code and return a pdf if exist
    @GetMapping("/pdf/{fileCode}")
    public ResponseEntity<?> downloadFile(@PathVariable("fileCode") String fileCode) {
         
        ResponseEntity<?> response = downloadService.getPdf(fileCode);
        return response;
              
    }
}