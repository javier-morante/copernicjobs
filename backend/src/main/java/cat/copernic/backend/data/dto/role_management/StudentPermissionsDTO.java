package cat.copernic.backend.data.dto.role_management;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentPermissionsDTO {
    
    private boolean myOffers;
    private boolean createOffer;
    private boolean incidents;
    private boolean laboralInformation;
    private boolean offers;
    private boolean myInscriptions;    

}
