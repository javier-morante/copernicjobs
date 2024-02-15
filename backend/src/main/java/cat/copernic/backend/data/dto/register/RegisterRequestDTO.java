package cat.copernic.backend.data.dto.register;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestDTO {
    
    private String companyName;

    private String email;

    private String contactPhone;
    
    private String nif;

    private String password;
}
