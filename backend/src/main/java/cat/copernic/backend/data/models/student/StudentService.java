package cat.copernic.backend.data.models.student;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface StudentService {

    void save(Student student);

    List<Student> getAll();

    Student getById(Integer id);

    List<Student> getByNewOfferNotification();

    void update(Student student);

    void deleteById(Integer id);

    Student getByEmail(String email);
    
}
