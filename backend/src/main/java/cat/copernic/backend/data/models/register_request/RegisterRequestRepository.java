package cat.copernic.backend.data.models.register_request;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


public interface RegisterRequestRepository extends JpaRepository<RegisterRequest, Integer> {

    Optional<RegisterRequest> findByNif(String nif);
    Optional<RegisterRequest> findByEmail(String email);
}
