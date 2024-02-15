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

import cat.copernic.backend.data.dto.user_management.CreateCompanyDTO;
import cat.copernic.backend.data.dto.user_management.UpdateCompanyDTO;
import cat.copernic.backend.data.responses.Response;
import cat.copernic.backend.data.responses.ResponseState;
import cat.copernic.backend.services.user_management.CompanyManagementService;

@RestController
@RequestMapping("/api/user-management/company")
public class CompanyManagementController {
 
    @Autowired
    private CompanyManagementService companyManagementService;

    @PostMapping("/create-company")
    public ResponseEntity<?> createCompany(
        @RequestBody CreateCompanyDTO body
    ) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        
        Response res = this.companyManagementService.createCompany(body);
        int responseCode = (res.getStatus() == ResponseState.OK)? 200 : 400 ;

        return ResponseEntity.status(responseCode).body(
            res.getBody()
        );
    }

    @GetMapping("/get-companies")
    public ResponseEntity<?> getCompanies() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        Response res = this.companyManagementService.getCompanies();
        int responseCode = (res.getStatus() == ResponseState.OK)? 200 : 400;

        return ResponseEntity.status(responseCode).body(
            res.getBody()
        );
    }

    @PostMapping("/update-company")
    public ResponseEntity<?> updateCompany(
        @RequestBody UpdateCompanyDTO body
    ) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        Response res = this.companyManagementService.updateCompany(body);
        int responseCode = (res.getStatus() == ResponseState.OK)? 200 : 400;

        return ResponseEntity.status(responseCode).body(
            res.getBody()
        );
    }

}
