package cat.copernic.backend.services.upload.lambda;

import org.springframework.web.multipart.MultipartFile;

@FunctionalInterface
public interface UpLoadFilter {

    public abstract boolean filter(MultipartFile file);
}