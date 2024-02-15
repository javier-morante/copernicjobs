package cat.copernic.backend.data.models.student;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface StudentRepository extends JpaRepository<Student,Integer>{
    
    Optional<Student> findByEmail(String email);

    @Query("SELECT stu FROM Student AS stu WHERE stu.newOfferNotification = true")
    List<Student> getStudentsByOfferNotificationStatus();
}
