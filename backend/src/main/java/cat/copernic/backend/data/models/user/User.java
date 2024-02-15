     package cat.copernic.backend.data.models.user;

import java.sql.Date;

import cat.copernic.backend.data.enums.UserRole;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    protected Integer userId;

    @Column(name = "nif", nullable = false,unique = true)
    protected String nif;

    @Column(name = "name", nullable = false)
    protected String name;

    @Column(name = "email", nullable = false, unique = true)
    protected String email;

    @Column(name = "phone")
    protected String phone;

    @Column(name = "password", nullable = false,columnDefinition = "TEXT")
    protected String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "name_role")
    protected UserRole userRole;

    @Column(name = "last_login")
    protected Date lastLogin;

    @Column(name = "is_enabled")
    protected Boolean isEnabled;

    public User(String name, String email,String nif ,String phone, String password, UserRole userRole, Date lastLogin, boolean isEnabled) {
        this.name = name;
        this.email = email;
        this.nif = nif;
        this.phone = phone;
        this.password = password;
        this.userRole = userRole;
        this.lastLogin = lastLogin;
        this.isEnabled = isEnabled;
    }

    
    public boolean bequals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
    
        User user = (User) object;
    
        if (userId != null ? !userId.equals(user.userId) : user.userId != null) return false;
        if (nif != null ? !nif.equals(user.nif) : user.nif != null) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (userRole != user.userRole) return false;
        return true;
    }
    
    
}