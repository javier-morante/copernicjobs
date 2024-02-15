package cat.copernic.backend.services.utils.validators.profesional_profile;


import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cat.copernic.backend.data.dto.student_profesional_profile.StudentProfesionalProfileDTO;
import cat.copernic.backend.services.utils.validators.profesional_profile.Exceptions.InvalidCodeExeption;
import cat.copernic.backend.services.utils.validators.user_profiles.ValidateCompanyData;
import cat.copernic.backend.services.utils.validators.user_profiles.exceptions.InvalidURLException;


@Service
public class ValidateProfesionalProfile  {

    private final String codeRegex = "^\\w{8}$";

    private final String isEmptyV = "^\\s*$";

    Logger logger = LoggerFactory.getLogger(ValidateProfesionalProfile.class);
    
    @Autowired
    ValidateCompanyData validateSites;

    public void validateProfesionalProfile(StudentProfesionalProfileDTO studentPP)throws InvalidURLException, InvalidCodeExeption{

        if (!validateSites.validateWebPageURL(studentPP.getLinkedinUrl())) {
            if(!this.isEmpty(studentPP.getGithubUrl())) throw new InvalidURLException("Linkedin url is not correct");
        }

        if (!validateSites.validateWebPageURL(studentPP.getPortfolioUrl())) {
            if(!this.isEmpty(studentPP.getGithubUrl())) throw new InvalidURLException("Portfolio url is not correct");
        }

        if (!validateSites.validateWebPageURL(studentPP.getYoutubeUrl())) {
            if(!this.isEmpty(studentPP.getGithubUrl())) throw new InvalidURLException("Youtube url is not correct");
        }

        if (!validateSites.validateWebPageURL(studentPP.getGithubUrl())) {
            
            if(!this.isEmpty(studentPP.getGithubUrl())) throw new InvalidURLException("Github url is not correct");
        }

        if (!this.validateCode(studentPP.getCurriculumPath())) {
           if(!this.isEmpty(studentPP.getCurriculumPath())) throw new InvalidCodeExeption("The File code is not correct");
        }

    }

    public boolean validateCode(String curriculumPath) {
        return Pattern.compile(codeRegex).matcher(curriculumPath).find();
    }

    public boolean isEmpty(String matchString){
        return Pattern.compile(this.isEmptyV).matcher(matchString).find();
    }

}
