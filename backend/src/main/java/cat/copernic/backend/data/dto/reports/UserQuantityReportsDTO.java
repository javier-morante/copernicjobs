package cat.copernic.backend.data.dto.reports;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserQuantityReportsDTO {
    
    private Integer studentsQuantity;
    private Integer companyQuantity;
    private Integer administratorQuantity;
    
}