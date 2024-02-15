package cat.copernic.backend.data.models.register_request;

import cat.copernic.backend.data.enums.RequestStatus;
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
@Table(name = "register_request")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "request_id")
    private Integer id; 

    @Column(name = "company_name", nullable = false,length = 50)
    private String companyName;

    @Column(name = "email", nullable = false,length = 50)
    private String email;

    @Column(name = "contact_phone", nullable = false,length = 50)
    private String contactPhone;

    @Column(name = "nif", nullable = false,length = 50)
    private String nif;

    @Column(name = "password", nullable = false,columnDefinition = "TEXT")
    private String password;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private RequestStatus status = RequestStatus.WAITING;

     @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((companyName == null) ? 0 : companyName.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((contactPhone == null) ? 0 : contactPhone.hashCode());
        result = prime * result + ((status == null) ? 0 : status.hashCode());
        result = prime * result + ((nif == null) ?0: nif.hashCode());
        return result;
    }

    @Override
public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;

    RegisterRequest request = (RegisterRequest) obj;
    
    if (id != null ? !id.equals(request.id) : request.id != null) return false;
    if (companyName != null ? !companyName.equals(request.companyName) : request.companyName != null) return false;
    if (email != null ? !email.equals(request.email) : request.email != null) return false;
    if (contactPhone != null ? !contactPhone.equals(request.contactPhone) : request.contactPhone != null) return false;
    if (nif != null ? !nif.equals(request.nif) : request.nif != null) return false;
    return status == request.status;
}



}


