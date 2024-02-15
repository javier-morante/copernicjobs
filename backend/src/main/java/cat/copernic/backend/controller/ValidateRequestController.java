package cat.copernic.backend.controller;

import org.springframework.web.bind.annotation.RestController;

import cat.copernic.backend.data.dto.register.RegisterRequestDTONoPass;
import cat.copernic.backend.data.enums.RequestStatus;
import cat.copernic.backend.data.models.Response;
import cat.copernic.backend.services.register.validate_register.ValidateRequestService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/register")
public class ValidateRequestController {
    
    @Autowired
    private ValidateRequestService requestService;

    @PostMapping("/validation-request")
    public ResponseEntity<?> validateRegister(@RequestBody RegisterRequestDTONoPass registerRequestDTONoPass) {
        Response response = new Response(400, "error has ocurred"); 
        if (registerRequestDTONoPass.getStatus() == RequestStatus.VALIDATED) {
            response = this.requestService.validateRequest(registerRequestDTONoPass);
        }else if (registerRequestDTONoPass.getStatus() == RequestStatus.REJECTED) {
            response = this.requestService.declineRequest(registerRequestDTONoPass);
        }
        
        return ResponseEntity.status(response.getStatus()).body(response.getBody());
    }
    
}
