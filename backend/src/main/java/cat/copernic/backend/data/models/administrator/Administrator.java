package cat.copernic.backend.data.models.administrator;

import java.sql.Date;

import cat.copernic.backend.data.enums.UserRole;
import cat.copernic.backend.data.models.user.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "administrator")
@PrimaryKeyJoinColumn(name = "user_id")
@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@NoArgsConstructor
public class Administrator extends User {

    @Column(name = "new_access_request_notification")
    private Boolean newAccessRequestNotification = true;

    @Column(name = "new_report_notification")
    private Boolean newReportNotification = true;

    public Administrator(String name, String email,String nif,String phone, String password,
            Date lastLogin, boolean isEnabled, Boolean newAccessRequestNotification, Boolean newReportNotification) {
        super(name, email,nif ,phone, password, UserRole.ADMINISTRATOR, lastLogin, isEnabled);
        this.newAccessRequestNotification = newAccessRequestNotification;
        this.newReportNotification = newReportNotification;
    }
    
}
