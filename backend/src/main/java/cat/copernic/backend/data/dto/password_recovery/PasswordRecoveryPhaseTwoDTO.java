package cat.copernic.backend.data.dto.password_recovery;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PasswordRecoveryPhaseTwoDTO {
    
    private String email;
    private String code;
    private String password;
    private String repeatPassword;

}
