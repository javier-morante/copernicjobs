package cat.copernic.backend.data.dto.companyProfileModal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyProfileModalDTO {
    private String companyName;
    private String companyDescription;
    private String phone;
    private String webpage;
}
