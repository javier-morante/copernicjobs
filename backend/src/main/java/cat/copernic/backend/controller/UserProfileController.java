package cat.copernic.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;

import cat.copernic.backend.data.dto.UserProfile.AdministratorProfileDTO;
import cat.copernic.backend.data.dto.UserProfile.CompanyProfileDTO;
import cat.copernic.backend.data.dto.UserProfile.StudentProfileDTO;
import cat.copernic.backend.data.responses.Response;
import cat.copernic.backend.data.responses.ResponseState;
import cat.copernic.backend.services.user_profile.UserProfileService;

@RestController
@RequestMapping("/api/user-profile")
public class UserProfileController {
    
    @Autowired
    private UserProfileService userProfileService;

    // GET Endpoint : Returns the user data depending on the role in a JSON string format 
    @GetMapping("/get-user-data")
    public ResponseEntity<?> getUserData() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();    

        Response res = this.userProfileService.getUserData(auth);

        if(res.getStatus() == ResponseState.OK) {
            return ResponseEntity.status(200).body(
                res.getBody()
            );
        }

        return ResponseEntity.status(400).body(
            res.getBody()
        );
    }

    // POST Endpoint : Updates the Student data via the provided one
    @PostMapping("/set-student-data")
    public ResponseEntity<?> setUserData(
        @RequestBody StudentProfileDTO body
    ) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        Response res = this.userProfileService.setStudentData(auth, body);

        if(res.getStatus() == ResponseState.OK) {
            return ResponseEntity.status(200).body(
                res.getBody()
            );
        }
        
        return ResponseEntity.status(400).body(
            res.getBody()
        );
    }

    // POST Endpoint : Updates the Company data via the provided one
    @PostMapping("/set-company-data")
    public ResponseEntity<?> setCompanyData(
        @ModelAttribute CompanyProfileDTO body
    ) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        Response res = this.userProfileService.setCompanyData(auth, body);
        
        if(res.getStatus() == ResponseState.OK) {
            return ResponseEntity.status(200).body(
                res.getBody()
            );
        }
        
        return ResponseEntity.status(400).body(
            res.getBody()
        );
    }

    // POST Endpoint : Update the Administrator data via the provided one
    @PostMapping("/set-administrator-data")
    public ResponseEntity<?> setAdministratorData(
        @RequestBody AdministratorProfileDTO body
    ) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        Response res = this.userProfileService.setAdministratorData(auth, body);
        
        if(res.getStatus() == ResponseState.OK) {
            return ResponseEntity.status(200).body(
                res.getBody()
            );
        }
        
        return ResponseEntity.status(400).body(
            res.getBody()
        );
    }

}
