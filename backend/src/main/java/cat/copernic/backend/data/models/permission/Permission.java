package cat.copernic.backend.data.models.permission;

import cat.copernic.backend.data.enums.UserRole;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "permissions")
public class Permission {
    @Id  
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "permission_id")
    private int id ;

    @Column(name = "permission_name",length = 50  )
    private String name;

    @Column(name = "role_name")
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Column(name = "is_enabled")
    private Boolean isEnabled; 
}
