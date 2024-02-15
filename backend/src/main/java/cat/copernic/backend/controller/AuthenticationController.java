package cat.copernic.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cat.copernic.backend.data.dto.authentication.SignInDTO;
import cat.copernic.backend.data.responses.Response;
import cat.copernic.backend.data.responses.ResponseState;
import cat.copernic.backend.services.sign_in.SignInService;

@RestController
@RequestMapping("/api/authentication")
public class AuthenticationController {
    
    @Autowired
    private SignInService signInService;
    
    @PostMapping("/sign-in")
    public ResponseEntity<?> signIn(
        @RequestBody SignInDTO signInDTO
    ) {
        Response signInResponse = this.signInService.signIn(signInDTO);

        if(signInResponse.getStatus() == ResponseState.OK) {
            return ResponseEntity.status(200).body(
                signInResponse.getBody()
            );
        }

        return ResponseEntity.status(401).body(
            signInResponse.getBody()
        );
    }

}
