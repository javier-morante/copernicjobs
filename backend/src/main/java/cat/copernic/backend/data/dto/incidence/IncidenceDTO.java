package cat.copernic.backend.data.dto.incidence;

import com.fasterxml.jackson.annotation.JsonFormat;

import cat.copernic.backend.data.enums.IncidenceCategory;
import cat.copernic.backend.data.enums.IncidenceStatus;
import cat.copernic.backend.data.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IncidenceDTO {

    private Integer id;

    private String title;

    private String description;

    private IncidenceCategory category;

    private IncidenceStatus status;

    private String userName;

    private UserRole userRole;

    @JsonFormat(pattern="yyyy-MM-dd")
    private String creationDate;

}