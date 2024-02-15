package cat.copernic.backend.services.utils.validators.offer_creation;

import org.springframework.stereotype.Service;

import cat.copernic.backend.data.models.offer.Offer;
import cat.copernic.backend.services.utils.validators.offer_creation.exceptions.InvalidDescriptionException;
import cat.copernic.backend.services.utils.validators.offer_creation.exceptions.InvalidLocationException;
import cat.copernic.backend.services.utils.validators.offer_creation.exceptions.InvalidSalaryIntervalException;
import cat.copernic.backend.services.utils.validators.offer_creation.exceptions.InvalidTitleException;

@Service
public class ValidateOfferData {
    
    private final int TITLE_LENGHT = 80;

    private final int LOCATION_LENGHT = 100;

    private final int SHORT_DESCRIPTION_LENGHT = 200;

    private final int REQUIREMENTS_DESCRIPTION_LENGHT = 800;

    private final int FUNCTIONS_DESCRIPTION_LENGHT = 800;

    private final int TECHNOLOGIES_DESCRIPTION_LENGHT = 800;

    private final int SALARY_INTERVAL_LENGHT = 50;


    public void validateOffer(Offer offer) 
        throws InvalidTitleException, InvalidLocationException, 
        InvalidDescriptionException, InvalidSalaryIntervalException {
     
        if(!validateTitle(offer.getTitle())) {
            throw new InvalidTitleException("Title to long!");
        }

        if(!validateLocation(offer.getLocation())) {
            throw new InvalidLocationException("Location to long!");
        }

        if(!validateDescription(offer.getShortDescription())) {
            throw new InvalidDescriptionException("Description to long!");
        }

        if(!validateRequirements(offer.getRequirementsDescription())) {
            throw new InvalidDescriptionException("Requirements description to long!");
        }

        if(!validateFunctions(offer.getFunctionsDescription())) {
            throw new InvalidDescriptionException("Functions description to long!");
        }

        if(!validateTechnologies(offer.getTechnologiesDescription())) {
            throw new InvalidDescriptionException("Technologies description to long!");
        }

        if(!validateSalaryInterval(offer.getSalaryInterval())) {
            throw new InvalidSalaryIntervalException("Salary to long!");
        }

    }


    public boolean validateTitle(String title) {
        return title.length() <= TITLE_LENGHT && title.length() > 0;
    }

    public boolean validateLocation(String location) {
        return location.length() <= LOCATION_LENGHT && location.length() > 0;
    }

    public boolean validateDescription(String shortDescription) {
        return shortDescription.length() <= SHORT_DESCRIPTION_LENGHT && shortDescription.length() > 0;
    }

    public boolean validateRequirements(String requirements) {
        return requirements.length() <= REQUIREMENTS_DESCRIPTION_LENGHT && requirements.length() > 0;
    }

    public boolean validateFunctions(String functions) {
        return functions.length() <= FUNCTIONS_DESCRIPTION_LENGHT && functions.length() > 0;
    }

    public boolean validateTechnologies(String technologies) {
        return technologies.length() <= TECHNOLOGIES_DESCRIPTION_LENGHT && technologies.length() > 0;
    }

    public boolean validateSalaryInterval(String salaryInterval) {
        return salaryInterval.length() <= SALARY_INTERVAL_LENGHT && salaryInterval.length() > 0;
    }

}
