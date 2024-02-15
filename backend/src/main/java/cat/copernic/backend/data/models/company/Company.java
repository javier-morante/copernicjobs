 package cat.copernic.backend.data.models.company;

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
@Table(name = "company")
@PrimaryKeyJoinColumn(name = "user_id")
@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@NoArgsConstructor
public class Company extends User {

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "icon_path", columnDefinition = "TEXT")
    private String iconPath;

    @Column(name = "web_page_url", columnDefinition = "TEXT")
    private String webPageUrl; 

    @Column(name = "resolved_report_notification", nullable = false)
    private boolean resolvedReportNotification = true;

    public Company(String name, String email,String nif, String phone, String password,Date lastLogin,
            boolean isEnabled, String description, String iconPath, String webPageUrl,
            boolean resolvedReportNotification) {
        super(name, email,nif, phone, password, UserRole.COMPANY, lastLogin, isEnabled);
        this.description = description;
        this.iconPath = iconPath;
        this.webPageUrl = webPageUrl;
        this.resolvedReportNotification = resolvedReportNotification;
    }
    
}