package cat.copernic.backend.data.dto.register;

import cat.copernic.backend.data.enums.RequestStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestDTONoPass {
    
    private String companyName;

    private String email;

    private String contactPhone;
    
    private String nif;
    
    private RequestStatus status = RequestStatus.WAITING;
}
