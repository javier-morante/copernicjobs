package cat.copernic.backend.data.models.user_password_reset_request;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPasswordResetRequestRepository extends JpaRepository<UserPasswordResetRequest,Integer>{
 
    Optional<UserPasswordResetRequest> findByEmail(String email);
    
    Optional<UserPasswordResetRequest> findByEmailAndCode(String email, String code);
}
