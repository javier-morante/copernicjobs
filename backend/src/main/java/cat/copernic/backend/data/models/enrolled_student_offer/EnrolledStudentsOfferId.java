package cat.copernic.backend.data.models.enrolled_student_offer;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class EnrolledStudentsOfferId {
    private Integer studentId;
    
    private Integer offerId;
}
