package cat.copernic.backend.services.role_management_module;

import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;
import java.util.HashMap;
import java.util.List;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import cat.copernic.backend.data.dto.role_management.AdministratorPermissionsDTO;
import cat.copernic.backend.data.dto.role_management.CompanyPermissionsDTO;
import cat.copernic.backend.data.dto.role_management.StudentPermissionsDTO;
import cat.copernic.backend.data.enums.UserRole;
import cat.copernic.backend.data.models.permission.Permission;
import cat.copernic.backend.data.models.permission.PermissionService;
import cat.copernic.backend.data.responses.Response;
import cat.copernic.backend.data.responses.ResponseState;

@Service
public class RoleManagementService {
    

    @Autowired
    private PermissionService permissionService;

    // Get all the role permissions of a provided role
    public Response getRolePermissions(UserRole role) {
        List<Permission> permissions = this.permissionService.getByRole(role);

        HashMap<String, Boolean> tmpMap = new HashMap<String, Boolean>();

        for (Permission permission : permissions) {
            tmpMap.put(
                permission.getName(), 
                permission.getIsEnabled()
            );
        }

        return new Response(
            ResponseState.ERROR,
            new Gson().toJson(tmpMap)
        );
    }


    public Response updateStudentPermissions(StudentPermissionsDTO body) {
        boolean status = this.updateRolePermissions(
            UserRole.STUDENT, 
            this.processRequestBody(body)
        );

        return new Response(
            (status)? ResponseState.OK : ResponseState.ERROR,
            (status)? "Updated Successfully" : "Error Updating data"
        );
    }

    public Response updateCompanyPermissions(CompanyPermissionsDTO body) {
        boolean status = this.updateRolePermissions(
            UserRole.COMPANY, 
            this.processRequestBody(body)
        );

        return new Response(
            (status)? ResponseState.OK : ResponseState.ERROR,
            (status)? "Updated Successfully" : "Error Updating data"
        );
    }

    public Response updateAdministratorPermissions(AdministratorPermissionsDTO body) {
        boolean status = this.updateRolePermissions(
            UserRole.ADMINISTRATOR, 
            this.processRequestBody(body)
        );

        return new Response(
            (status)? ResponseState.OK : ResponseState.ERROR,
            (status)? "Updated Successfully" : "Error Updating data"
        );
    }



    private boolean updateRolePermissions(UserRole actualRole, Map<String, Boolean> processedBody) {
        try {
            for (String key : processedBody.keySet()) {
                this.permissionService.updateStatusByPermissionAndRole(
                    processedBody.get(key), 
                    key,
                    actualRole
                );
            }
            return true;

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    private <T> Map<String, Boolean> processRequestBody(T body) {
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, Object>>(){}.getType();
        String jsonString = gson.toJson(body);
        return gson.fromJson(jsonString, type);
    }

}
