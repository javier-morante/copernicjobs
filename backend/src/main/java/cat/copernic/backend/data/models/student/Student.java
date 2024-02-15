 package cat.copernic.backend.data.models.student;

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
@Table(name = "student")
@PrimaryKeyJoinColumn(name = "user_id")
@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@NoArgsConstructor
public class Student extends User {
    

    @Column(name = "surname", nullable = false,length = 50)
    private String surname;

    @Column(name = "public_email", nullable = false)
    private Boolean publicEmail = true;

    @Column(name = "public_phone", nullable = false)
    private Boolean publicPhone = true;

    @Column(name = "public_linkedin_url", nullable = false)
    private Boolean publicLinkedinUrl = false;

    @Column(name = "public_portfolio_url", nullable = false)
    private Boolean publicPortfolioUrl = false;

    @Column(name = "public_youtube_url", nullable = false)
    private Boolean publicYoutubeUrl = false;

    @Column(name = "public_github_url", nullable = false)
    private Boolean publicGithubUrl = false;

    @Column(name = "new_offer_notification", nullable = false)
    private Boolean newOfferNotification = true;

    @Column(name = "new_inscription_notification", nullable = false)
    private Boolean newInscriptionNotification = true;

    public Student(String name, String email,String nif,String phone, String password, Date lastLogin,
            boolean isEnabled, String surname, Boolean publicPhone,
            Boolean publicLinkedinUrl, Boolean publicPortfolioUrl, Boolean publicYoutubeUrl, Boolean publicGithubUrl,
            Boolean newOfferNotification, Boolean newInscriptionNotification) {
        super(name, email, nif,phone, password, UserRole.STUDENT, lastLogin, isEnabled);
        this.surname = surname;
        this.publicPhone = publicPhone;
        this.publicLinkedinUrl = publicLinkedinUrl;
        this.publicPortfolioUrl = publicPortfolioUrl;
        this.publicYoutubeUrl = publicYoutubeUrl;
        this.publicGithubUrl = publicGithubUrl;
        this.newOfferNotification = newOfferNotification;
        this.newInscriptionNotification = newInscriptionNotification;
    }

}