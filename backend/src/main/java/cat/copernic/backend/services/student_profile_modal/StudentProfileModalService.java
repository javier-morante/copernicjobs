package cat.copernic.backend.services.student_profile_modal;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import cat.copernic.backend.data.dto.studentProfileModal.StudentProfileModalDTO;
import cat.copernic.backend.data.models.student.StudentService;
import cat.copernic.backend.data.models.student_professional_profile.StudentProfessionalProfile;
import cat.copernic.backend.data.models.student_professional_profile.StudentProfessionalProfileServiceImpl;
import cat.copernic.backend.data.responses.Response;
import cat.copernic.backend.data.responses.ResponseState;

@Service
public class StudentProfileModalService {
    @Autowired
    private StudentProfessionalProfileServiceImpl studentProfessionalProfileDao;
    
    @Autowired
    private StudentService studentDao;

    private ModelMapper modelMapper = new ModelMapper();

    public Response getStudentData(String email) {
        StudentProfessionalProfile studentProfessionalProfile = studentProfessionalProfileDao.getByStudent(studentDao.getByEmail(email));
        StudentProfileModalDTO studentModalDTO = modelMapper.map(studentProfessionalProfile, StudentProfileModalDTO.class); 
        studentModalDTO.setName(studentProfessionalProfile.getStudent().getName());
        studentModalDTO.setSurname(studentProfessionalProfile.getStudent().getSurname());
        studentModalDTO.setEmail(studentProfessionalProfile.getStudent().getEmail());
        studentModalDTO.setPhone(studentProfessionalProfile.getStudent().getPhone());
        studentModalDTO.setPublicEmail(studentProfessionalProfile.getStudent().getPublicEmail());
        studentModalDTO.setPublicPhone(studentProfessionalProfile.getStudent().getPublicPhone());
        studentModalDTO.setPublicLinkedinUrl(studentProfessionalProfile.getStudent().getPublicLinkedinUrl());
        studentModalDTO.setPublicPortfolioUrl(studentProfessionalProfile.getStudent().getPublicPortfolioUrl());
        studentModalDTO.setPublicYoutubeUrl(studentProfessionalProfile.getStudent().getPublicYoutubeUrl());
        studentModalDTO.setPublicGithubUrl(studentProfessionalProfile.getStudent().getPublicGithubUrl());

        return new Response(ResponseState.OK, new Gson().toJson(studentModalDTO));
    }
}
