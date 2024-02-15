package cat.copernic.backend.data.models.offer;


import java.sql.Date;

import cat.copernic.backend.data.enums.Urgency;
import cat.copernic.backend.data.enums.Workday;
import cat.copernic.backend.data.models.degree.Degree;
import cat.copernic.backend.data.models.user.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "offer")
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "offer_id")
    private Integer id; 

    @Column(name = "title", nullable = false,length = 80)
    private String title;

    @Column(name = "location", columnDefinition = "TEXT")
    private String location;

    @Column(name = "short_description", nullable = false, length = 255)
    private String shortDescription;

    @Column(name = "requirements_description", columnDefinition = "TEXT", nullable = false)
    private String requirementsDescription;

    @Column(name = "functions_description", columnDefinition = "TEXT")
    private String functionsDescription;

    @Column(name = "technologies_description", columnDefinition = "TEXT", nullable = false)
    private String technologiesDescription;

    @Enumerated(EnumType.STRING)
    @Column(name = "urgency", nullable = false)
    private Urgency urgency = Urgency.LOW;

    @Enumerated(EnumType.STRING)
    @Column(name = "workday")
    private Workday workday;

    @Column(name = "salary_interval", length = 50)
    private String salaryInterval;

    @Column(name = "vacancies_number")
    private Integer vacancies;

    @Column(name = "publishment_date")
    private Date publishmentDate;

    @Column(name = "is_enabled") 
    private Boolean isEnabled;

    @Column(name = "creation_date")
    private Date creationDate;

    @ManyToOne
    @JoinColumn(name = "degree_id", nullable = false)
    private Degree degree;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}

 