package cat.copernic.backend.data.dto.user_management;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateStudentDTO {

    private Integer userId;
    private String name;
    private String surname;
    private String nif;
    private String phone;
    private String email;

}