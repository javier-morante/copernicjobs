package cat.copernic.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cat.copernic.backend.data.responses.Response;
import cat.copernic.backend.data.responses.ResponseState;
import cat.copernic.backend.services.reports.ReportsService;

@RestController
@RequestMapping("/api/reports")
public class ReportsController {
    
    @Autowired
    private ReportsService reportsService;

    @GetMapping("/get-users-quantity")
    public ResponseEntity<?> getUsersQuantity() {
        Response res = this.reportsService.getUsersQuantity();
        int responseCode = (res.getStatus() == ResponseState.OK)? 200 : 400 ;

        return ResponseEntity.status(responseCode).body(
            res.getBody()
        );
    }

    @GetMapping("/get-enrollments-quantity")
    public ResponseEntity<?> getEnrollmentsQuantity() {
        Response res = this.reportsService.getEnrolledStudentQuantity();
        int responseCode = (res.getStatus() == ResponseState.OK)? 200 : 400 ;

        return ResponseEntity.status(responseCode).body(
            res.getBody()
        );
    }

    @GetMapping("/get-offer-publishments")
    public ResponseEntity<?> getOfferPublishments() {
        Response res = this.reportsService.getOfferPublishments();
        int responseCode = (res.getStatus() == ResponseState.OK)? 200 : 400 ;

        return ResponseEntity.status(responseCode).body(
            res.getBody()
        );
    }

    @GetMapping("/get-user-traffic")
    public ResponseEntity<?> getUserTraffic() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        Response res = this.reportsService.getUserTraffic();
        int responseCode = (res.getStatus() == ResponseState.OK)? 200 : 400 ;

        return ResponseEntity.status(responseCode).body(
            res.getBody()
        );
    }

}
