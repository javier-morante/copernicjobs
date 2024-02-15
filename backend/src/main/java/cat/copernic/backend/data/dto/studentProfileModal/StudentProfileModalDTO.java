package cat.copernic.backend.data.dto.studentProfileModal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentProfileModalDTO {
    private String curriculumPath;
    private String linkedinUrl;
    private String portfolioUrl;
    private String youtubeUrl;
    private String githubUrl;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private boolean publicEmail;
    private boolean publicPhone;
    private boolean publicLinkedinUrl;
    private boolean publicPortfolioUrl;
    private boolean publicYoutubeUrl;
    private boolean publicGithubUrl;
}
