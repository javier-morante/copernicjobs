package cat.copernic.backend.data.dto.role_management;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleManagementDTO {

    private String userRole;
    private List<PermissionDTO> permissions;
    
}
