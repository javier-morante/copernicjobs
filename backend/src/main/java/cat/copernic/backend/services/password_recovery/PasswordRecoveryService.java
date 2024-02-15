package cat.copernic.backend.services.password_recovery;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import cat.copernic.backend.data.dto.password_recovery.PasswordRecoveryPhaseOneDTO;
import cat.copernic.backend.data.dto.password_recovery.PasswordRecoveryPhaseTwoDTO;
import cat.copernic.backend.data.enums.ResetRequestStatus;
import cat.copernic.backend.data.models.user.User;
import cat.copernic.backend.data.models.user.UserService;
import cat.copernic.backend.data.models.user_password_reset_request.UserPasswordResetRequest;
import cat.copernic.backend.data.models.user_password_reset_request.UserPasswordResetRequestService;
import cat.copernic.backend.data.responses.Response;
import cat.copernic.backend.data.responses.ResponseState;
import cat.copernic.backend.services.mailing.EmailService;

@Service
public class PasswordRecoveryService {
    
    @Autowired
    private UserService userService;

    @Autowired
    private UserPasswordResetRequestService passwordResetService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmailService emailService;

    // Phase One of the password recovery process, where the request is validated and if accepted
    // the sever generates the necesary recovery data in the DB cache
    public Response passwordRecoveryPhaseOne(PasswordRecoveryPhaseOneDTO body) {
        String code = this.generateCode();

        if(code != null) {
            UserPasswordResetRequest userResetRequest = new UserPasswordResetRequest(
                body.getEmail(),
                code, 
                ResetRequestStatus.WAITING, 
                new Date(System.currentTimeMillis())
            );

            this.verifyPreviousUserRequests(body.getEmail());

            if(this.validateEmail(body.getEmail())) {
                this.passwordResetService.save(userResetRequest);

                // Send Email
                this.emailService.passwordRecoveryEmail(
                    body.getEmail(),
                    code
                );

                return new Response(
                    ResponseState.OK,
                    "Password Recovery Request Accepted"
                );
            }

            return new Response(ResponseState.ERROR, "The email does not exist");
        }

        return new Response(ResponseState.ERROR, "General Error");
    }

    // Phase Two of the password recovery process, where the email code is verified and the user 
    // password is updated
    public Response passwordRecoveryPhaseTwo(PasswordRecoveryPhaseTwoDTO body) {
        UserPasswordResetRequest request = this.passwordResetService.getUserPasswordRegisterRequestByEmailAndCode(
            body.getEmail(),
            body.getCode()
        );

        if(request != null && this.validatePassword(body.getPassword(), body.getRepeatPassword())) {
            User user = this.userService.getByEmail(body.getEmail());
            user.setPassword(this.passwordEncoder.encode(body.getPassword()));
            this.userService.update(user);

            return new Response(ResponseState.OK, "Password updated Successfully");
        }

        return new Response(ResponseState.ERROR, "Password didn't updated Successfully");
    }

    // Validate the existance of the user email inside of the users database
    private boolean validateEmail(String email) {
        User user = this.userService.getByEmail(email);
        return user != null;
    }

    // Verify previous password recovery request assigned to the request user
    private void verifyPreviousUserRequests(String email) {
        UserPasswordResetRequest request = this.passwordResetService.getUserPasswordRegisterRequestByEmail(email);

        if(request != null) {
            this.passwordResetService.deleteUserPasswordResetRequestById(request.getUserPasswordResetRequestId());
        }
    }

    // Validate the correct password format
    private boolean validatePassword(String password, String repeatPassword) {
        if(password.equals(repeatPassword)) {
            Pattern pattern = Pattern.compile("^(?=.{1,50}$).");
            Matcher matcher = pattern.matcher(password);
    
            return matcher.find();

            //return true;
        }

        return false;
    }

    // Generate the recovery token
    private String generateCode() {
        String resultCode = ""; 
        LocalDateTime now = LocalDateTime.now();
        String dateTimeString = now.toString();

        try {
            
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(dateTimeString.getBytes(StandardCharsets.UTF_8));
            
            for (int x = 0; x < 6; x++) {
                resultCode += String.format("%02x", hashBytes[x]);
            }

            return resultCode.substring(0, 6);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}