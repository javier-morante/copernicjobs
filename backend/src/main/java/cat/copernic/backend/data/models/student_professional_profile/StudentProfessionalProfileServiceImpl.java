package cat.copernic.backend.data.models.student_professional_profile;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cat.copernic.backend.data.models.student.Student;

@Service
public class StudentProfessionalProfileServiceImpl implements StudentProfessionalProfileService{

    @Autowired
    private StudentProfessionalProfileRepository studentProfessionalProfileRepository;

    @Override
    public void save(StudentProfessionalProfile studentProfessionalProfile) {
        this.studentProfessionalProfileRepository.save(studentProfessionalProfile);
    }

    @Override
    public List<StudentProfessionalProfile> getAll() {
        return this.studentProfessionalProfileRepository.findAll();
    }

    @Override
    public StudentProfessionalProfile getById(Integer id) {
        return this.studentProfessionalProfileRepository.findById(id).orElse(null);
    }

    @Override
    public void update(StudentProfessionalProfile studentProfessionalProfile) {
        this.save(studentProfessionalProfile);
    }

    @Override
    public void deleteById(Integer id) {
        this.studentProfessionalProfileRepository.deleteById(id);
    }

    @Override
    public StudentProfessionalProfile getByStudent(Student student) {
       return this.studentProfessionalProfileRepository.findByStudent(student).orElse(null);
    }

}
