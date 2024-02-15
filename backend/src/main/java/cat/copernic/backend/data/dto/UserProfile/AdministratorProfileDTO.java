package cat.copernic.backend.data.dto.UserProfile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdministratorProfileDTO {
    
    private String name;
    private String email;
    private String phone;

    private Boolean newAccessRequestNotification;
    private Boolean newReportNotification;

}
