package cat.copernic.backend.data.dto.offers;

import cat.copernic.backend.data.enums.Urgency;
import cat.copernic.backend.data.enums.Workday;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateOfferDTO {
    
    private Integer id;
    private String title;
    private String location;
    private String shortDescription;
    private String requirementsDescription;
    private String functionsDescription;
    private String technologiesDescription;
    private Urgency urgency;
    private Workday workday;
    private String degree;
    private String salaryInterval;
    private Integer vacancies;
    
}
