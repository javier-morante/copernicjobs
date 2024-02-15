package cat.copernic.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import cat.copernic.backend.data.models.Response;

import cat.copernic.backend.services.companyProfileModal.CompanyProfileModalService;

@RestController
@RequestMapping("/api/company")
public class CompanyProfileModalController {
    @Autowired
    private CompanyProfileModalService companyProfileModalService;

    @GetMapping("/get-by-nif")
    public ResponseEntity<?> getCompanyById(
        @RequestParam String nif
    ) {
        try {
            Response res = companyProfileModalService.getCompanyData(nif);
            return ResponseEntity.status(200).body(res.getBody());

        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(200).body("The user is not a company!");
        }

    }
}
