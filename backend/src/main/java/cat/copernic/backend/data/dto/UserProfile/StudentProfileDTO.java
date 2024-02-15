package cat.copernic.backend.data.dto.UserProfile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentProfileDTO {
    
    private String name;
    private String surname;
    private String nif;
    private String email;
    private String phone;

    private Boolean publicEmail;
    private Boolean publicPhone;
    private Boolean publicLinkedinUrl;
    private Boolean publicPortfolioUrl;
    private Boolean publicYoutubeUrl;
    private Boolean publicGithubUrl;

    private Boolean newOfferNotification;
    private Boolean newInscriptionNotification;

}
