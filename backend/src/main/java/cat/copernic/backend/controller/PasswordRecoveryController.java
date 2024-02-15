package cat.copernic.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cat.copernic.backend.data.dto.password_recovery.PasswordRecoveryPhaseOneDTO;
import cat.copernic.backend.data.dto.password_recovery.PasswordRecoveryPhaseTwoDTO;
import cat.copernic.backend.data.responses.Response;
import cat.copernic.backend.data.responses.ResponseState;
import cat.copernic.backend.services.password_recovery.PasswordRecoveryService;

@RestController
@RequestMapping("/api/password-recovery")
public class PasswordRecoveryController {
    
    @Autowired
    private PasswordRecoveryService passwordRecoveryService;

    // POST Endpoint : Phase one of the password validation process
    @PostMapping("/phase-one")
    public ResponseEntity<?> phaseOne(
        @RequestBody PasswordRecoveryPhaseOneDTO body
    ) {
        Response passwordRecoveryResponse = this.passwordRecoveryService.passwordRecoveryPhaseOne(body);

        if(passwordRecoveryResponse.getStatus() == ResponseState.OK) {
            return ResponseEntity.status(200).body(
                passwordRecoveryResponse.getBody()
            );
        }

        return ResponseEntity.status(400).body(
            passwordRecoveryResponse.getBody()
        );
    }

    // POST Endpoint : Phase Two of the password validation process
    @PostMapping("/phase-two")
    public ResponseEntity<?> phaseTwo(
        @RequestBody PasswordRecoveryPhaseTwoDTO body
    ) {
        Response passwordRecoveryResponse = this.passwordRecoveryService.passwordRecoveryPhaseTwo(body);

        if(passwordRecoveryResponse.getStatus() == ResponseState.OK) {
            return ResponseEntity.status(200).body(
                passwordRecoveryResponse.getBody()
            );
        }

        return ResponseEntity.status(400).body(
            passwordRecoveryResponse.getBody()
        );
    }

}
