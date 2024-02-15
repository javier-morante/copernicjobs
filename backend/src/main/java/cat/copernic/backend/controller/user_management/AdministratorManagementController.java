package cat.copernic.backend.controller.user_management;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cat.copernic.backend.data.dto.user_management.CreateAdministratorDTO;
import cat.copernic.backend.data.dto.user_management.UpdateAdministratorDTO;
import cat.copernic.backend.data.responses.Response;
import cat.copernic.backend.data.responses.ResponseState;
import cat.copernic.backend.services.user_management.AdministratorManagementService;

@RestController
@RequestMapping("/api/user-management/administrator")
public class AdministratorManagementController {
    
    @Autowired AdministratorManagementService administratorManagementService;

    @PostMapping("/create-administrator")
    public ResponseEntity<?> createAdministrator(
        @RequestBody CreateAdministratorDTO body
    ) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        
        Response res = this.administratorManagementService.createAdministrator(body);
        int responseCode = (res.getStatus() == ResponseState.OK)? 200 : 400 ;

        return ResponseEntity.status(responseCode).body(
            res.getBody()
        );
    }

    @GetMapping("/get-administrators")
    public ResponseEntity<?> getAdministrators() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        Response res = this.administratorManagementService.getAdministrators();
        int responseCode = (res.getStatus() == ResponseState.OK)? 200 : 400;

        return ResponseEntity.status(responseCode).body(
            res.getBody()
        );
    }

    @PostMapping("/update-administrator")
    public ResponseEntity<?> updateAdministrator(
        @RequestBody UpdateAdministratorDTO body
    ) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        Response res = this.administratorManagementService.updateAdministrator(body);
        int responseCode = (res.getStatus() == ResponseState.OK)? 200 : 400;

        return ResponseEntity.status(responseCode).body(
            res.getBody()
        );
    }

}