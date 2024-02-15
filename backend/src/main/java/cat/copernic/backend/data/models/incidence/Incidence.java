package cat.copernic.backend.data.models.incidence;

import jakarta.persistence.CascadeType;
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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonFormat;

import cat.copernic.backend.data.enums.IncidenceCategory;
import cat.copernic.backend.data.enums.IncidenceStatus;
import cat.copernic.backend.data.models.user.User;


@Entity
@Table(name = "incidence")
// @Getter
// @Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Incidence {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "incidence_id")
    private Integer id;

    @Column(name = "title", length = 50)
    private String title;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;  
 
    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    private IncidenceCategory category;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private IncidenceStatus status;

    @Column(name = "creation_date", columnDefinition = "DATE")
    @JsonFormat(pattern="yyyy-MM-dd")
    private String creationDate;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    @JoinColumn(name = "user_id")
    private User user; 

    public Incidence(String title, String description, IncidenceCategory category, IncidenceStatus status, User user) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.status = status;
        this.user = user;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((category == null) ? 0 : category.hashCode());
        result = prime * result + ((status == null) ? 0 : status.hashCode());
        result = prime * result + ((creationDate == null) ?0: creationDate.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;

        Incidence incidence = (Incidence) obj;
        
        if (id != null ? !id.equals(incidence.id) : incidence.id != null) return false;
        if (title != null ? !title.equals(incidence.title) : incidence.title != null) return false;
        if (description != null ? !description.equals(incidence.description) : incidence.description != null) return false;
        return creationDate != null ? creationDate.equals(incidence.creationDate) : incidence.creationDate != null;
    }


}
