package cat.copernic.backend.data.models.degree;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DegreeRepository extends JpaRepository<Degree, Integer> {

    Optional<Degree> findByName(String name);
    
}
