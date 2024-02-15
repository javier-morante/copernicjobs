package cat.copernic.backend.data.dto.incidence;


import cat.copernic.backend.data.enums.IncidenceCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IncidenceNoIdDTO {

    private String title;

    private String description;

    private IncidenceCategory category;

}
