package cat.copernic.backend.data.models.degree;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "degree")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Degree {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "degree_id")
    private Integer degreeId;

    @Column(name = "name", length = 50)
    private String name;

    public Degree(String name) {
        this.name = name;
    }
    
}
