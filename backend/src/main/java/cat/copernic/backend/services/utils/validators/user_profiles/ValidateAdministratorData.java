package cat.copernic.backend.services.utils.validators.user_profiles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cat.copernic.backend.data.dto.user_management.CreateAdministratorDTO;
import cat.copernic.backend.data.models.administrator.Administrator;
import cat.copernic.backend.data.models.user.User;
import cat.copernic.backend.data.models.user.UserService;
import cat.copernic.backend.services.utils.validators.user_profiles.exceptions.InvalidDNIException;
import cat.copernic.backend.services.utils.validators.user_profiles.exceptions.InvalidEmailException;
import cat.copernic.backend.services.utils.validators.user_profiles.exceptions.InvalidPhoneException;
import cat.copernic.backend.services.utils.validators.user_profiles.exceptions.InvalidUserNameException;

@Service
public class ValidateAdministratorData {
    
    @Autowired
    private UserService userService;

    @Autowired
    private ValidateUserData validateUserData;

    public void validateAdministratorCreationData(CreateAdministratorDTO createAdministratorDTO) 
        throws InvalidUserNameException, InvalidPhoneException, InvalidEmailException {

        if(!validateUserData.validateName(createAdministratorDTO.getName())) {
            throw new InvalidUserNameException("Name to Long!");
        }

        if(!validateUserData.validateEmail(createAdministratorDTO.getEmail())) {
            throw new InvalidPhoneException("Invalid Phone!");
        }

        if(!validateUserData.validatePhone(createAdministratorDTO.getPhone())) {
            throw new InvalidEmailException("Invalid Email!");
        }
    }


    public void validateAdministrator(Administrator administrator) 
        throws InvalidUserNameException, InvalidPhoneException, InvalidEmailException, 
        InvalidDNIException {

        User user = this.userService.getById(administrator.getUserId());
        this.validateUserData.validateUser(user);

    }
    
}
