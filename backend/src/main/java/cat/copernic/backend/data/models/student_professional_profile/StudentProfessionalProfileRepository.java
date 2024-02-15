package cat.copernic.backend.data.models.student_professional_profile;

import org.springframework.data.jpa.repository.JpaRepository;
import cat.copernic.backend.data.models.student.Student;
import java.util.Optional;


public interface StudentProfessionalProfileRepository extends JpaRepository<StudentProfessionalProfile,Integer>{

    Optional<StudentProfessionalProfile> findByStudent(Student student);
    
}
