package cat.copernic.backend.data.models.student;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService{

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public void save(Student student) {
        this.studentRepository.save(student);
    }

    @Override
    public List<Student> getAll() {
        return this.studentRepository.findAll();
    }

    @Override
    public List<Student> getByNewOfferNotification() {
        return this.studentRepository.getStudentsByOfferNotificationStatus();
    }

    @Override
    public Student getById(Integer id) {
        return this.studentRepository.findById(id).orElse(null);
    }

    @Override
    public void update(Student student) {
        this.save(student);
    }

    @Override
    public void deleteById(Integer id) {
        this.studentRepository.deleteById(id);
    }

    @Override
    public Student getByEmail(String email) {
        return this.studentRepository.findByEmail(email).orElse(null);
    }

}
