package cat.copernic.backend.data.dto.role_management;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PermissionDTO {
    
    private String permissionName;
    private Boolean permissionStatus;

}
