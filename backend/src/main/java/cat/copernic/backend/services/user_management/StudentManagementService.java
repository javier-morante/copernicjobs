package cat.copernic.backend.services.user_management;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import cat.copernic.backend.data.dto.user_management.CreateStudentDTO;
import cat.copernic.backend.data.dto.user_management.StudentDTO;
import cat.copernic.backend.data.dto.user_management.UpdateStudentDTO;
import cat.copernic.backend.data.models.student.Student;
import cat.copernic.backend.data.models.student.StudentService;
import cat.copernic.backend.data.responses.Response;
import cat.copernic.backend.data.responses.ResponseState;
import cat.copernic.backend.services.utils.validators.user_profiles.ValidateStudentData;
import cat.copernic.backend.services.utils.validators.user_profiles.exceptions.InvalidDNIException;
import cat.copernic.backend.services.utils.validators.user_profiles.exceptions.InvalidEmailException;
import cat.copernic.backend.services.utils.validators.user_profiles.exceptions.InvalidPhoneException;
import cat.copernic.backend.services.utils.validators.user_profiles.exceptions.InvalidSurnameException;
import cat.copernic.backend.services.utils.validators.user_profiles.exceptions.InvalidUserNameException;

@Service
public class StudentManagementService {
    
    @Autowired
    private StudentService studentService;

    @Autowired
    private ValidateStudentData validateStudentData;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public Response createStudent(CreateStudentDTO body) {
        try {
            this.validateStudentData.validateStudentCreationData(body);

            this.studentService.save(new Student(
                body.getName(), 
                body.getEmail(), 
                body.getNif(), 
                body.getPhone(), 
                this.passwordEncoder.encode(body.getPassword()),  
                null, 
                true, 
                body.getSurname(),
                false, 
                false, 
                false, 
                false, 
                false,
                true, 
                true
            ));

            return new Response(
                ResponseState.OK,
                "Student user Created!"
            );

        } catch (InvalidUserNameException | InvalidPhoneException | InvalidEmailException |
            InvalidSurnameException | InvalidDNIException ex) {
            return new Response(
                ResponseState.ERROR,
                ex.getMessage()
            );

        }catch (Exception ex) {
            return new Response(
                ResponseState.ERROR,
                "Not Ok"
            );
        }
    }

    public Response getUsers() {
        try {
            List<Student> students = this.studentService.getAll();
            List<StudentDTO> processedStudents = new ArrayList<StudentDTO>();

            for (Student student : students) {
                processedStudents.add(new StudentDTO(
                    student.getUserId(),
                    student.getName(),
                    student.getSurname(),
                    student.getNif(),
                    student.getPhone(),
                    student.getEmail(),
                    student.getIsEnabled()
                ));
            }

            return new Response(
                ResponseState.OK,
                new Gson().toJson(processedStudents)
            );

        } catch (Exception e) {
            return new Response(
                ResponseState.ERROR,
                "Can't fetch the users"
            );
        }
    }

    public Response updateStudent(UpdateStudentDTO body) {
        try {
            Student student = this.studentService.getById(body.getUserId());

            student.setName(body.getName());
            student.setSurname(body.getSurname());
            student.setNif(body.getNif());
            student.setPhone(body.getPhone());
            student.setEmail(body.getEmail());

            this.validateStudentData.validateStudent(student);

            this.studentService.update(student);

            return new Response(
                ResponseState.OK,
                "User Updated Successfully!"
            );

        } catch (InvalidUserNameException | InvalidPhoneException | InvalidEmailException |
            InvalidSurnameException | InvalidDNIException ex) {
            return new Response(
                ResponseState.ERROR,
                ex.getMessage()
            );

        } catch (Exception ex) {
            ex.printStackTrace();
            return new Response(
                ResponseState.ERROR,
                ex.getMessage()
            );
        }
    }

}
