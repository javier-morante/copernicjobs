package cat.copernic.backend.services.profesional_profile;

import java.io.IOException;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import cat.copernic.backend.data.dto.student_profesional_profile.StudentProfesionalProfileDTO;
import cat.copernic.backend.data.models.Response;
import cat.copernic.backend.data.models.degree.Degree;
import cat.copernic.backend.data.models.degree.DegreeService;
import cat.copernic.backend.data.models.student.Student;
import cat.copernic.backend.data.models.student.StudentService;
import cat.copernic.backend.data.models.student_professional_profile.StudentProfessionalProfile;
import cat.copernic.backend.data.models.student_professional_profile.StudentProfessionalProfileService;
import cat.copernic.backend.services.upload.UploadService;
import cat.copernic.backend.services.upload.exceptions.FileMistmatchTypeException;
import cat.copernic.backend.services.upload.exceptions.FileTypeNotExistException;
import cat.copernic.backend.services.utils.validators.profesional_profile.ValidateProfesionalProfile;
import cat.copernic.backend.services.utils.validators.profesional_profile.Exceptions.InvalidCodeExeption;
import cat.copernic.backend.services.utils.validators.user_profiles.exceptions.InvalidURLException;

@Service
public class ProfesionalProfileService {

    @Autowired
    ValidateProfesionalProfile vProfesionalProfile;

    @Autowired
    StudentProfessionalProfileService serviceImpl;

    @Autowired
    DegreeService degreeService;

    @Autowired
    StudentService service;

    @Autowired
    UploadService uploadService;

    Logger logger = LoggerFactory.getLogger(ProfesionalProfileService.class);

    ModelMapper modelMapper = new ModelMapper();

    /**
     * Method takes an email and returns the profesional profile
     * if user email not exist return an error espired
     *
     * @param email of student
     * @return response of the profersional profile or an error
     */
    public Response getProfesionalProfile(String email) {

        Student student = service.getByEmail(email);

        if (student == null) {
            return new Response(400, "Error has ocurred");
        }

        StudentProfessionalProfile studentProfessionalProfile = serviceImpl.getByStudent(student);

        studentProfessionalProfile = createIfNotExist(studentProfessionalProfile, student);

        StudentProfesionalProfileDTO studentDTO = modelMapper.map(studentProfessionalProfile,
                StudentProfesionalProfileDTO.class);

        studentDTO.setDegree(studentProfessionalProfile.getDegree().getName());

        return new Response(200, new Gson().toJson(studentDTO));

    }

    /**
     * Method takes a student profesinal profile and an email and updates it
     * 
     * @param studentPPDTO
     * @param email        of student
     * @return a response with ok or "Error has ocurred"
     */
    public Response updateProfesionalProfile(StudentProfesionalProfileDTO studentPPDTO, String email) {
        try {
            vProfesionalProfile.validateProfesionalProfile(studentPPDTO);
            

            Student student = service.getByEmail(email);

            if (student == null) {
                return new Response(400, "Error has ocurred");
            }

            StudentProfessionalProfile studentPP = this.serviceImpl.getByStudent(student);
            StudentProfessionalProfile studentPP2 = modelMapper.map(studentPPDTO, StudentProfessionalProfile.class);
            Degree degree = degreeService.getByName(studentPPDTO.getDegree());
            studentPP2.setDegree(degree);
            String code = null;

            if (studentPPDTO.getCurriculum() != null) {
                if(!studentPPDTO.getCurriculum().isEmpty()) 
                code = uploadService.save("pdf", 
                                    studentPP.getCurriculumPath(), 
                                    studentPPDTO.getCurriculum());
            }

            if (studentPP != null)
                studentPP2.setId(studentPP.getId());

            if (code != null)
                studentPP2.setCurriculumPath(code);

            studentPP2.setStudent(student);



            serviceImpl.update(studentPP2);

            return new Response(200, "ok");

        } catch (FileTypeNotExistException | FileMistmatchTypeException | InvalidURLException | InvalidCodeExeption e) {
            return new Response(400,e.getMessage());
        }catch(IOException ioe){
            return new Response(400,"Ha ocorregut un error");
        }
    }

    /**
     * Method check if exist an studen profesional profile and returns a new if
     * doesn't exist
     * 
     * @param studentProfessionalProfile
     * @param student
     * @return
     */
    public StudentProfessionalProfile createIfNotExist(StudentProfessionalProfile studentProfessionalProfile,
            Student student) {
        if (studentProfessionalProfile == null) {
            Degree degree = this.degreeService.getById(1);
            studentProfessionalProfile = new StudentProfessionalProfile("", "", "", "", "",degree, student);
            this.serviceImpl.save(studentProfessionalProfile);
            return studentProfessionalProfile;
        }

        return studentProfessionalProfile;
    }
}
