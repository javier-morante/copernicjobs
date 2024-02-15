package cat.copernic.backend.controller;

import org.springframework.web.bind.annotation.RestController;

import cat.copernic.backend.data.dto.register.RegisterRequestDTO;
import cat.copernic.backend.data.models.Response;
import cat.copernic.backend.services.register.register_request.RegisterRequestPrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.security.core.Authentication;

@RestController
@RequestMapping("/api/register")
public class RequestRegisterController {

    @Autowired
    private RegisterRequestPrService serviceRequest;
    
    @PostMapping("/register-access")
    public ResponseEntity<?> registerRquest(@RequestBody RegisterRequestDTO rb){

        Response res = this.serviceRequest.save(rb);
        return ResponseEntity.status(res.getStatus()).body(res.getBody());
    }

    @GetMapping("/get-all")
    public ResponseEntity<?> getRegisters() {
         Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        Response res = serviceRequest.getRegisterRequest();
        return ResponseEntity.status(res.getStatus()).body(res.getBody());
    } 
}

