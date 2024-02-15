package cat.copernic.backend.services.utils.validators.user_profiles;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import cat.copernic.backend.data.models.user.User;
import cat.copernic.backend.services.utils.validators.user_profiles.exceptions.InvalidEmailException;
import cat.copernic.backend.services.utils.validators.user_profiles.exceptions.InvalidPhoneException;
import cat.copernic.backend.services.utils.validators.user_profiles.exceptions.InvalidUserNameException;
 
@Service
public class ValidateUserData {
    
    private final int NAME_MAX_LENGTH = 20;

    private final String PHONE_PATTERN = "^[+]?[(]{0,1}[0-9]{1,4}[)]{0,1}[0-9]{0,13}";

    private final String EMAIL_PATTERN = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$";


    // Validates the full user attributes
    public void validateUser(User user) 
        throws InvalidUserNameException, InvalidPhoneException, InvalidEmailException {
        
        if(!validateName(user.getName())) {
            throw new InvalidUserNameException("Name to Long!");
        }

        if(!validatePhone(user.getPhone())) {
            throw new InvalidPhoneException("Invalid Phone!");
        }

        if(!validateEmail(user.getEmail())) {
            throw new InvalidEmailException("Invalid Email!");
        }
    }


    // Validates the First name of the user
    public boolean validateName(String name) {
        return name.length() <= NAME_MAX_LENGTH;
    }

    // Validates the phone number of the user
    public boolean validatePhone(String phone) {
        Pattern pattern = Pattern.compile(PHONE_PATTERN);
        Matcher matcher = pattern.matcher(phone);

        return matcher.matches();
    }

    // Validates the email address of the user
    public boolean validateEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }



    
}
