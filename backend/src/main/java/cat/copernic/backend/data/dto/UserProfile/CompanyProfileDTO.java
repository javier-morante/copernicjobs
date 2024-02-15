package cat.copernic.backend.data.dto.UserProfile;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyProfileDTO {

    private String name;
    private String email;
    private String phone;
    private String nif;

    private String description;
    private String webPageUrl;
    private String iconPath;
    private MultipartFile image;

    private Boolean resolvedReportNotification;

}
