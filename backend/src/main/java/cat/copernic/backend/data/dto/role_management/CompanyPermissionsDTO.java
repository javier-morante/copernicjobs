package cat.copernic.backend.data.dto.role_management;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyPermissionsDTO {

    private Boolean myOffers;
    private Boolean createOffer;
    private Boolean incidents;
    private Boolean offers;

}
