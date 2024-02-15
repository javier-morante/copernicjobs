package cat.copernic.backend.data.dto.navBar;

import cat.copernic.backend.data.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PermissionsDTO {
    
    private String name;
    private UserRole role;
    private Boolean isEnabled;

}
