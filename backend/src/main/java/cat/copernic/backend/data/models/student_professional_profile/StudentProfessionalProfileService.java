package cat.copernic.backend.data.models.student_professional_profile;

import java.util.List;

import org.springframework.stereotype.Service;

import cat.copernic.backend.data.models.student.Student;

@Service
public interface StudentProfessionalProfileService {
    
    void save(StudentProfessionalProfile studentProfessionalProfile);

    List<StudentProfessionalProfile> getAll();

    StudentProfessionalProfile getById(Integer id);

    void update(StudentProfessionalProfile studentProfessionalProfile);

    void deleteById(Integer id);

    StudentProfessionalProfile getByStudent(Student student);
    
}
