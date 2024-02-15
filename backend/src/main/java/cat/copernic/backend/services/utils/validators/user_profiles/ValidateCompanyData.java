package cat.copernic.backend.services.utils.validators.user_profiles;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cat.copernic.backend.data.dto.user_management.CreateCompanyDTO;
import cat.copernic.backend.data.models.company.Company;
import cat.copernic.backend.data.models.user.User;
import cat.copernic.backend.data.models.user.UserService;
import cat.copernic.backend.services.utils.validators.user_profiles.exceptions.InvalidCIFException;
import cat.copernic.backend.services.utils.validators.user_profiles.exceptions.InvalidDNIException;
import cat.copernic.backend.services.utils.validators.user_profiles.exceptions.InvalidDescriptionException;
import cat.copernic.backend.services.utils.validators.user_profiles.exceptions.InvalidEmailException;
import cat.copernic.backend.services.utils.validators.user_profiles.exceptions.InvalidPhoneException;
import cat.copernic.backend.services.utils.validators.user_profiles.exceptions.InvalidURLException;
import cat.copernic.backend.services.utils.validators.user_profiles.exceptions.InvalidUserNameException;

@Service
public class ValidateCompanyData {
    
    private final int DESCRIPTION_LENGHT = 200;

    private final String WEB_PAGE_URL_PATTERN = 
        "^https?:\\/\\/(www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b([-a-zA-Z0-9()!@:%_\\+.~#?&\\/\\/=]*)$";

    private final String CIF_PATTERN =
            // "^[A-Z]{1}[0-9]{7}[A-Z0-9]{1}$";
        "^([ABCDEFGHJKLMNPQRSUVW])(\\d{7})([0-9A-J])$";


    @Autowired
    private ValidateUserData validateUserData;

    @Autowired
    private UserService userService;


    public void validateCompanyCreationData(CreateCompanyDTO createCompanyDTO) 
        throws InvalidUserNameException, InvalidPhoneException, InvalidEmailException, 
        InvalidDNIException {

        if(!validateUserData.validateName(createCompanyDTO.getName())) {
            throw new InvalidUserNameException("Name to Long!");
        }

        if(!validateUserData.validateEmail(createCompanyDTO.getEmail())) {
            throw new InvalidPhoneException("Invalid Phone!");
        }

        if(!validateUserData.validatePhone(createCompanyDTO.getPhone())) {
            throw new InvalidEmailException("Invalid Email!");
        }

        if(!validateCIF(createCompanyDTO.getNif())) {
            throw new InvalidDNIException("Invalid DNI Format!");
        }
        
    }

    public void validateCompany(Company company) 
        throws InvalidUserNameException, InvalidPhoneException, InvalidEmailException, 
        InvalidDescriptionException, InvalidURLException, InvalidCIFException {
     
        User user = this.userService.getById(company.getUserId());
        this.validateUserData.validateUser(user);

        if(!validateDescription(company.getDescription())) {
            throw new InvalidDescriptionException("Invalid Description!");
        }

        if(!validateWebPageURL(company.getWebPageUrl())) {
            throw new InvalidURLException("Invalid URL format!");
        }

        if(!validateCIF(user.getNif())) {
            throw new InvalidCIFException("Invalid CIF format!");
        }
    }


    // Validates the Compny Description
    public boolean validateDescription(String description) {
        return description.length() <= DESCRIPTION_LENGHT && description.length() > 0;
    }


    // Validates the Company Web Page URL
    public boolean validateWebPageURL(String webPageURL) {
        Pattern pattern = Pattern.compile(WEB_PAGE_URL_PATTERN);
        Matcher matcher = pattern.matcher(webPageURL);

        return matcher.matches();
    }


    // Validate the CIF format
    public boolean validateCIF(String cif) {
        Pattern pattern = Pattern.compile(CIF_PATTERN);
        Matcher matcher = pattern.matcher(cif);

        return matcher.matches();
    }

}
