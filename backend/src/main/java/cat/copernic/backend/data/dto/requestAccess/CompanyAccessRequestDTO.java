package cat.copernic.backend.data.dto.requestAccess;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyAccessRequestDTO {
    
    private String companyName;
    private String email;
    private String phone;
    private String nif;
    private String password;

}
