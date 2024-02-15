package cat.copernic.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cat.copernic.backend.data.dto.role_management.AdministratorPermissionsDTO;
import cat.copernic.backend.data.dto.role_management.CompanyPermissionsDTO;
import cat.copernic.backend.data.dto.role_management.StudentPermissionsDTO;
import cat.copernic.backend.data.enums.UserRole;
import cat.copernic.backend.data.responses.Response;
import cat.copernic.backend.data.responses.ResponseState;
import cat.copernic.backend.services.role_management_module.RoleManagementService;

@RestController
@RequestMapping("/api/role_management")
public class RoleManagementController {
    
    @Autowired
    private RoleManagementService roleManagementService;

    @GetMapping("/get-role-permissions")
    public ResponseEntity<?> getStudentRoles(
        @RequestParam String userRole
    ) {
        Response response = this.roleManagementService.getRolePermissions(
            UserRole.valueOf(userRole)
        );

        return ResponseEntity.status(200).body(
            response.getBody()
        );
    }

    @PostMapping("/update-student-permissions")
    public ResponseEntity<?> updateRolePermissions(
        @RequestBody StudentPermissionsDTO body
    ) {
        Response response = this.roleManagementService.updateStudentPermissions(body);

        boolean status = response.getStatus() == ResponseState.OK;

        return ResponseEntity.status((status)? 200 : 400).body(
            response.getBody()
        );
    }


    @PostMapping("/update-company-permissions")
    public ResponseEntity<?> updateRolePermissions(
        @RequestBody CompanyPermissionsDTO body
    ) {
        Response response = this.roleManagementService.updateCompanyPermissions(body);

        boolean status = response.getStatus() == ResponseState.OK;

        return ResponseEntity.status((status)? 200 : 400).body(
            response.getBody()
        );
    }

    @PostMapping("/update-administrator-permissions")
    public ResponseEntity<?> updateRolePermissions(
        @RequestBody AdministratorPermissionsDTO body
    ) {
        Response response = this.roleManagementService.updateAdministratorPermissions(body);

        boolean status = response.getStatus() == ResponseState.OK;

        return ResponseEntity.status((status)? 200 : 400).body(
            response.getBody()
        );
    }

}
