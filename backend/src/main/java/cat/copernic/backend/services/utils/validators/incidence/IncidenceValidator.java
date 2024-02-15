package cat.copernic.backend.services.utils.validators.incidence;

import org.springframework.stereotype.Service;

import cat.copernic.backend.data.models.incidence.Incidence;
import cat.copernic.backend.services.utils.validators.offer_creation.exceptions.InvalidTitleException;
import cat.copernic.backend.services.utils.validators.user_profiles.exceptions.InvalidDescriptionException;

@Service
public class IncidenceValidator {

    private final int DESCRIPTION_LENGHT = 100;

    private final int TITLE_LENGHT = 50;

    
    public void validateIncidence(Incidence incidence) throws InvalidDescriptionException, InvalidTitleException{

        if (!validateTitle(incidence.getTitle())) {
            throw new InvalidTitleException("El titul es molt llarg");
        }
        
        if (!validateDescription(incidence.getDescription())) {
            throw new InvalidDescriptionException("La descripció es molt llarga");
        }
    }

    public boolean validateDescription(String description) {
        return description.length() <= DESCRIPTION_LENGHT && description.length() > 0;
    }

    public boolean validateTitle(String description) {
        return description.length() <= TITLE_LENGHT && description.length() > 0;
    }
}
