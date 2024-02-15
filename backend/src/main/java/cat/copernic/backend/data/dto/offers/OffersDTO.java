package cat.copernic.backend.data.dto.offers;

import cat.copernic.backend.data.enums.Urgency;
import cat.copernic.backend.data.enums.Workday;
import cat.copernic.backend.data.models.degree.Degree;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OffersDTO {
    private Integer id;
    private String title;
    private String location;
    private String shortDescription;
    private String requirementsDescription;
    private String functionsDescription;
    private String technologiesDescription;
    private Urgency urgency;
    private Workday workday;
    private Degree degree;
    private String salaryInterval;
    private Integer vacancies;
    private String companyName;
    private String companyImage;
    private String companyNif;
    private Boolean isEnabled;
}
