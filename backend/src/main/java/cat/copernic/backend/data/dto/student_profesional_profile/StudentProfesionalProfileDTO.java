package cat.copernic.backend.data.dto.student_profesional_profile;


import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentProfesionalProfileDTO {
    
    private MultipartFile curriculum;

    private String curriculumPath;

    private String linkedinUrl;

    private String portfolioUrl;

    private String youtubeUrl;

    private String githubUrl;

    private String degree;


}
