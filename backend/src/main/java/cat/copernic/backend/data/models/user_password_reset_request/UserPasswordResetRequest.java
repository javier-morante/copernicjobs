 package cat.copernic.backend.data.models.user_password_reset_request;

import java.sql.Date;

import cat.copernic.backend.data.enums.ResetRequestStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_password_reset_request")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPasswordResetRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_password_reset_request_id")
    private Integer userPasswordResetRequestId;

    @Column(name = "email", length = 50)
    private String email;

    @Column(name = "code", length = 10)
    private String code;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ResetRequestStatus status;

    @Column(name = "request_date")
    private Date requestDate;

    public UserPasswordResetRequest(String email, String code, ResetRequestStatus status,
            Date requestDate) {
        this.email = email;
        this.code = code;
        this.status = status;
        this.requestDate = requestDate;
    }

}
