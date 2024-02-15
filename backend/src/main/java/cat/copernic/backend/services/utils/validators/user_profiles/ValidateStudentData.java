package cat.copernic.backend.services.utils.validators.user_profiles;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cat.copernic.backend.data.dto.user_management.CreateStudentDTO;
import cat.copernic.backend.data.models.student.Student;
import cat.copernic.backend.data.models.user.User;
import cat.copernic.backend.data.models.user.UserService;
import cat.copernic.backend.services.utils.validators.user_profiles.exceptions.InvalidDNIException;
import cat.copernic.backend.services.utils.validators.user_profiles.exceptions.InvalidEmailException;
import cat.copernic.backend.services.utils.validators.user_profiles.exceptions.InvalidPhoneException;
import cat.copernic.backend.services.utils.validators.user_profiles.exceptions.InvalidSurnameException;
import cat.copernic.backend.services.utils.validators.user_profiles.exceptions.InvalidUserNameException;

@Service
public class ValidateStudentData {
    
    private final int SURNAME_MAX_LENGTH = 20;

    private final String DNI_PATTERN = "^.{8}[A-Z]{1}$";

    @Autowired
    private ValidateUserData validateUserData;

    @Autowired
    private UserService userService;
    
    // Validate all the student data provided via the frontend form body
    public void validateStudentCreationData(CreateStudentDTO createStudentDTO) 
        throws InvalidUserNameException, InvalidPhoneException, InvalidEmailException,
        InvalidSurnameException, InvalidDNIException {

        if(!validateUserData.validateName(createStudentDTO.getName())) {
            throw new InvalidUserNameException("Name to Long!");
        }

        if(!validateUserData.validateEmail(createStudentDTO.getEmail())) {
            throw new InvalidPhoneException("Invalid Phone!");
        }

        if(!validateUserData.validatePhone(createStudentDTO.getPhone())) {
            throw new InvalidEmailException("Invalid Email!");
        }

        if(!validateSurname(createStudentDTO.getSurname())) {
            throw new InvalidSurnameException("Surname to Long!");
        }

        if(!validateDNI(createStudentDTO.getNif())) {
            throw new InvalidDNIException("Invalid DNI Format!");
        }
    }

    // Validate an existing student entity
    public void validateStudent(Student student) 
        throws InvalidUserNameException, InvalidPhoneException, InvalidEmailException,
        InvalidSurnameException, InvalidDNIException {

        User user = this.userService.getById(student.getUserId());
        validateUserData.validateUser(user);

        
        if(!validateSurname(student.getSurname())) {
            throw new InvalidSurnameException("Surname to Long!");
        }

        if(!validateDNI(student.getNif())) {
            throw new InvalidDNIException("Invalid DNI Format!");
        }
    }

    // Validates the Surname of the user
    public boolean validateSurname(String surname) {
        return surname.length() <= SURNAME_MAX_LENGTH; 
    }


    // Validate the student DNI input field format
    public boolean validateDNI(String dni) {
        Pattern pattern = Pattern.compile(DNI_PATTERN);
        Matcher matcher = pattern.matcher(dni);

        return matcher.matches();
    }


}
