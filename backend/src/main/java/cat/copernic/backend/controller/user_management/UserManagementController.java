package cat.copernic.backend.controller.user_management;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cat.copernic.backend.data.dto.user_management.UpdateUserPasswordDTO;
import cat.copernic.backend.data.responses.Response;
import cat.copernic.backend.data.responses.ResponseState;
import cat.copernic.backend.services.user_management.UserManagementService;

@RestController
@RequestMapping("/api/user-management")
public class UserManagementController {
    
    @Autowired
    private UserManagementService userManagementService;


    @GetMapping("/delete-user")
    public ResponseEntity<?> deleteUser(
        @RequestParam Integer userId
    ) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        Response res = this.userManagementService.deleteUser(auth, userId);
        int responseCode = (res.getStatus() == ResponseState.OK)? 200 : 400 ;

        return ResponseEntity.status(responseCode).body(
            res.getBody()
        );
    }

    @GetMapping("/lock-user")
    public ResponseEntity<?> lockUser(
        @RequestParam Integer userId    
    ) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        Response res = this.userManagementService.lockUser(auth, userId);
        int responseCode = (res.getStatus() == ResponseState.OK)? 200 : 400 ;

        return ResponseEntity.status(responseCode).body(
            res.getBody()
        );
    }

    @PostMapping("/update-user-password")
    public ResponseEntity<?> updateUserPassword(
        @RequestBody UpdateUserPasswordDTO body
    ) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        Response res = this.userManagementService.updateUserPassword(body);
        int responseCode = (res.getStatus() == ResponseState.OK)? 200 : 400 ;

        return ResponseEntity.status(responseCode).body(
            res.getBody()
        );
    }

}
