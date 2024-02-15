package cat.copernic.backend.services.user_profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import cat.copernic.backend.data.dto.UserProfile.AdministratorProfileDTO;
import cat.copernic.backend.data.dto.UserProfile.CompanyProfileDTO;
import cat.copernic.backend.data.dto.UserProfile.StudentProfileDTO;
import cat.copernic.backend.data.models.administrator.Administrator;
import cat.copernic.backend.data.models.administrator.AdministratorService;
import cat.copernic.backend.data.models.company.Company;
import cat.copernic.backend.data.models.company.CompanyService;
import cat.copernic.backend.data.models.student.Student;
import cat.copernic.backend.data.models.student.StudentService;
import cat.copernic.backend.data.models.user.User;
import cat.copernic.backend.data.models.user.UserService;
import cat.copernic.backend.data.responses.Response;
import cat.copernic.backend.data.responses.ResponseState;
import cat.copernic.backend.services.upload.UploadService;
import cat.copernic.backend.services.utils.validators.user_profiles.ValidateAdministratorData;
import cat.copernic.backend.services.utils.validators.user_profiles.ValidateCompanyData;
import cat.copernic.backend.services.utils.validators.user_profiles.ValidateStudentData;
import cat.copernic.backend.services.utils.validators.user_profiles.exceptions.InvalidCIFException;
import cat.copernic.backend.services.utils.validators.user_profiles.exceptions.InvalidDNIException;
import cat.copernic.backend.services.utils.validators.user_profiles.exceptions.InvalidDescriptionException;
import cat.copernic.backend.services.utils.validators.user_profiles.exceptions.InvalidEmailException;
import cat.copernic.backend.services.utils.validators.user_profiles.exceptions.InvalidPhoneException;
import cat.copernic.backend.services.utils.validators.user_profiles.exceptions.InvalidSurnameException;
import cat.copernic.backend.services.utils.validators.user_profiles.exceptions.InvalidURLException;
import cat.copernic.backend.services.utils.validators.user_profiles.exceptions.InvalidUserNameException;

import org.modelmapper.ModelMapper;

@Service
public class UserProfileService {

    @Autowired
    private UserService userService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private AdministratorService administratorService;

    @Autowired
    private ValidateStudentData validateStudentData;

    @Autowired
    private ValidateCompanyData validateCompanyData;

    @Autowired
    private ValidateAdministratorData validateAdministratorData;

    @Autowired
    private UploadService uploadService;


    private final ModelMapper modelMapper = new ModelMapper();

    // Returns the Response with the operation of the data fetch
    public Response getUserData(Authentication auth) {
        String data = this.getUserDataByRole(auth.getName());
        if(data != null) {
            return new Response(
                ResponseState.OK,
                data
            );
        }

        return new Response(
            ResponseState.ERROR,
            "An error ocurred fetching the user data"
        );
    }

    // Gets the user data based in the user role
    public String getUserDataByRole(String email) {
        User user = this.userService.getByEmail(email);

        if(user != null) {
            switch (user.getUserRole()) {
                case STUDENT :
                    return this.getStudentProfile(
                        this.studentService.getById(user.getUserId())
                    );

                case COMPANY :
                    return this.getCompanyProfile(
                        this.companyService.getById(user.getUserId())
                    );

                case ADMINISTRATOR :
                    return this.getAdministratorProfile(
                        this.administratorService.getById(user.getUserId())
                    );
            }
        }

        return null;
    }

    // Map the Student data into the StudentProfileDTO data class
    public String getStudentProfile(Student student) {
        StudentProfileDTO studentProfile = modelMapper.map(student, StudentProfileDTO.class);
        return new Gson().toJson(studentProfile);
    }

    // Map the Company data into the CompanyProfileDTO data class
    public String getCompanyProfile(Company company) {
        CompanyProfileDTO companyProfile = this.modelMapper.map(company, CompanyProfileDTO.class);
        return new Gson().toJson(companyProfile);
    }

    // Map the Administrator data into the AdministratorProfileDTO data class
    public String getAdministratorProfile(Administrator administrator) {
        AdministratorProfileDTO administratorProfile = this.modelMapper.map(administrator, AdministratorProfileDTO.class);        
        return new Gson().toJson(administratorProfile);
    }


    // --------------------------------------------------------------------------------

    public Response setStudentData(Authentication auth, StudentProfileDTO body) {
        try {
            User user = this.userService.getByEmail(auth.getName());
            Student student = this.studentService.getById(user.getUserId());

            student.setName(body.getName());
            student.setSurname(body.getSurname());
            student.setEmail(body.getEmail());
            student.setPhone(body.getPhone());
            student.setNif(body.getNif());
            student.setPublicEmail(body.getPublicEmail());
            student.setPublicPhone(body.getPublicPhone());
            student.setPublicLinkedinUrl(body.getPublicLinkedinUrl());
            student.setPublicPortfolioUrl(body.getPublicPortfolioUrl());
            student.setPublicYoutubeUrl(body.getPublicYoutubeUrl());
            student.setPublicGithubUrl(body.getPublicGithubUrl());
            student.setNewOfferNotification(body.getNewOfferNotification());
            student.setNewInscriptionNotification(body.getNewInscriptionNotification());

            this.validateStudentData.validateStudent(student);

            this.studentService.update(student);

            return new Response(
                ResponseState.OK,
                "Student User Updated Successfully!"
            );

        } catch (InvalidUserNameException | InvalidPhoneException | 
            InvalidEmailException | InvalidSurnameException | InvalidDNIException ex) {
            
            //ex.printStackTrace();
            return new Response(
                ResponseState.ERROR,
                ex.getMessage()
            );

        } catch (Exception ex) {
            ex.printStackTrace();
            return new Response(
                ResponseState.ERROR,
                "Invalid data, check all the fields!"
            );
        }
    } 

    public Response setCompanyData(Authentication auth, CompanyProfileDTO body) {
        try {
            System.out.println(body);
            User user = this.userService.getByEmail(auth.getName());
            Company company = this.companyService.getById(user.getUserId());
            if (body.getImage() != null) {
                if(!body.getImage().isEmpty())
                uploadService.save("image", 
                                    company.getIconPath(), 
                                    body.getImage());
            }

            company.setName(body.getName());
            company.setEmail(body.getEmail());
            company.setPhone(body.getPhone());
            company.setNif(body.getNif());
            company.setDescription(body.getDescription());
            if(this.uploadService.getFileUrl() != null)company.setIconPath(this.uploadService.getFileUrl());
            company.setWebPageUrl(body.getWebPageUrl());
            company.setResolvedReportNotification(body.getResolvedReportNotification());

            this.validateCompanyData.validateCompany(company);

            this.companyService.update(company);

            return new Response(
                ResponseState.OK,
                "Company User Updated Successfully!"
            );

        } catch (InvalidUserNameException | InvalidPhoneException | InvalidEmailException | 
            InvalidDescriptionException | InvalidURLException | InvalidCIFException ex) {

            //ex.printStackTrace();
            return new Response(
                ResponseState.ERROR,
                ex.getMessage()
            );

        } catch (Exception ex) {
            System.out.println("\n\n"+ex.getMessage());
            return new Response(
                ResponseState.ERROR,
                "Invalid data, check all the fields!"
            );
        }
    }

    public Response setAdministratorData(Authentication auth, AdministratorProfileDTO body) {
        try {
            User user = this.userService.getByEmail(auth.getName());
            Administrator administrator = this.administratorService.getById(user.getUserId());

            administrator.setName(body.getName());
            administrator.setEmail(body.getEmail());
            administrator.setPhone(body.getPhone());
            administrator.setNewAccessRequestNotification(body.getNewAccessRequestNotification());
            administrator.setNewReportNotification(body.getNewReportNotification());

            this.validateAdministratorData.validateAdministrator(administrator);

            this.administratorService.update(administrator);

            return new Response(
                ResponseState.OK,
                "Administrator User Updated Successfully!"
            );
            
        } catch (InvalidUserNameException | InvalidPhoneException | InvalidEmailException | 
            InvalidDNIException ex) {
                
            //ex.printStackTrace();
            return new Response(
                ResponseState.ERROR,
                ex.getMessage()
            );
        
        } catch (Exception ex) {
            return new Response(
                ResponseState.ERROR,
                "Invalid data, check all the fields!"
            );
        }
    }
}
