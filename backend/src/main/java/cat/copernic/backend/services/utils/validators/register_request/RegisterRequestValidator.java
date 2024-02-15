package cat.copernic.backend.services.utils.validators.register_request;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cat.copernic.backend.data.dto.register.RegisterRequestDTONoPass;
import cat.copernic.backend.data.models.register_request.RegisterRequest;
import cat.copernic.backend.data.models.register_request.RegisterRequestService;
import cat.copernic.backend.data.models.user.UserService;
import cat.copernic.backend.services.utils.validators.register_request.exceptions.IsNullException;
import cat.copernic.backend.services.utils.validators.user_profiles.ValidateCompanyData;
import cat.copernic.backend.services.utils.validators.user_profiles.ValidateUserData;
import cat.copernic.backend.services.utils.validators.user_profiles.exceptions.InvalidCIFException;
import cat.copernic.backend.services.utils.validators.user_profiles.exceptions.InvalidEmailException;
import cat.copernic.backend.services.utils.validators.user_profiles.exceptions.InvalidPhoneException;
import cat.copernic.backend.services.utils.validators.user_profiles.exceptions.InvalidUserNameException;
@Service
public class RegisterRequestValidator {

    @Autowired
    private UserService userDAO;

    @Autowired
    private RegisterRequestService registerRequestDAO;

    @Autowired
    ValidateUserData validateUserData;

    @Autowired
    ValidateCompanyData validateCompanyData;
    
    public void validateRegiaterRequest(RegisterRequest registerRequest) throws InvalidUserNameException, InvalidEmailException, InvalidPhoneException, InvalidCIFException, IsNullException{

        if (registerRequest == null) {
            throw new IsNullException("Ha ocorregut un error");
        }

        if (!this.validateUserData.validateName(registerRequest.getCompanyName())) {
            throw new InvalidUserNameException("El nom es molt llarg");
        }

        if (!this.validateUserData.validateEmail(registerRequest.getEmail())) {
            throw new InvalidEmailException("El email no es correcte");
        }

        if (this.isExistEmail(registerRequest.getEmail())) {
            throw new InvalidEmailException("El email existeix");
        }

        if (!this.validateUserData.validatePhone(registerRequest.getContactPhone())) {
            throw new InvalidPhoneException("El telefon no es correcte");
        } 

        if (!this.validateCompanyData.validateCIF(registerRequest.getNif())) {
            throw new InvalidCIFException("El cif no es correcte");
        }

        if (this.isExistNif(registerRequest.getNif())) {
            throw new InvalidCIFException("El cif existeix");
        }

    }
    
    
    public void validateRegiaterRequestDTONoPass(RegisterRequestDTONoPass registerRequestNoPass) throws InvalidUserNameException, InvalidEmailException, InvalidPhoneException, InvalidCIFException, IsNullException{

        if (registerRequestNoPass == null) {
            throw new IsNullException("Ha ocorregut un error");
        }

        if (!this.validateUserData.validateName(registerRequestNoPass.getCompanyName())) {
            throw new InvalidUserNameException("El nom es molt llarg");
        }

        if (!this.validateUserData.validateEmail(registerRequestNoPass.getEmail())) {
            throw new InvalidEmailException("El email no es correcte");
        }

        if (!this.validateUserData.validatePhone(registerRequestNoPass.getContactPhone())) {
            throw new InvalidPhoneException("El telefon no es correcte");
        } 

        if (!this.validateCompanyData.validateCIF(registerRequestNoPass.getNif())) {
            throw new InvalidCIFException("El cif no es correcte");
        }

    }
    
    

    private boolean isExistEmail(String email) {
        if (registerRequestDAO.getByEmail(email) != null) {
            return true;
        }
        if (userDAO.getByEmail(email) != null) {
            return true;
        }
        return false;
    }

    public Boolean isExistNif(String nif) {
        if (registerRequestDAO.getByNif(nif) != null) {

            return true;
        }

        if (userDAO.getByNif(nif) != null) {

            return true;
        }

        return false;
    }

}
