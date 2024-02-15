package cat.copernic.backend.data.models.student_professional_profile;

import cat.copernic.backend.data.models.degree.Degree;
import cat.copernic.backend.data.models.student.Student;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "student_professional_profile")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentProfessionalProfile {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_professional_profile_id")
    private Integer id;
    // REVISAR
    @Column(name = "curriculum_path",columnDefinition = "TEXT")
    private String curriculumPath;  

    @Column(name = "linkedin_url",columnDefinition = "TEXT")
    private String linkedinUrl;

    @Column(name = "portfolio_url",columnDefinition = "TEXT")
    private String portfolioUrl;

    @Column(name = "youtube_url",columnDefinition = "TEXT")
    private String youtubeUrl;

    @Column(name = "github_url",columnDefinition = "TEXT")
    private String githubUrl;

    @OneToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "degree_id", nullable = false)
    private Degree degree;


    public StudentProfessionalProfile(String curriculumPath, String linkedinUrl, String portfolioUrl, String youtubeUrl, String githubUrl,Degree degree ,Student student) {
        this.curriculumPath = curriculumPath;
        this.linkedinUrl = linkedinUrl;
        this.portfolioUrl = portfolioUrl;
        this.youtubeUrl = youtubeUrl;
        this.degree = degree;
        this.githubUrl = githubUrl;
        this.student = student;
    }
    
} 