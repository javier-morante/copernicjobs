package cat.copernic.backend.data.models.company;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
    
    Optional<Company> findByNif(String nif);
    
}
