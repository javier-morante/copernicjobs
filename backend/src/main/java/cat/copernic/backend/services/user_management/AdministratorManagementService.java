package cat.copernic.backend.services.user_management;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import cat.copernic.backend.data.dto.user_management.AdministratorDTO;
import cat.copernic.backend.data.dto.user_management.CreateAdministratorDTO;
import cat.copernic.backend.data.dto.user_management.UpdateAdministratorDTO;
import cat.copernic.backend.data.models.administrator.Administrator;
import cat.copernic.backend.data.models.administrator.AdministratorService;
import cat.copernic.backend.data.responses.Response;
import cat.copernic.backend.data.responses.ResponseState;
import cat.copernic.backend.services.utils.validators.user_profiles.ValidateAdministratorData;
import cat.copernic.backend.services.utils.validators.user_profiles.exceptions.InvalidEmailException;
import cat.copernic.backend.services.utils.validators.user_profiles.exceptions.InvalidPhoneException;
import cat.copernic.backend.services.utils.validators.user_profiles.exceptions.InvalidUserNameException;

@Service
public class AdministratorManagementService {
    
    @Autowired
    AdministratorService administratorService;

    @Autowired
    private ValidateAdministratorData validateAdministratorData;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Response createAdministrator(CreateAdministratorDTO body) {  
        try {
            this.validateAdministratorData.validateAdministratorCreationData(body);

            this.administratorService.save(new Administrator(
                body.getName(), 
                body.getEmail(), 
                body.getNif(), 
                body.getPhone(),
                this.passwordEncoder.encode(body.getPassword()), 
                null, 
                true, 
                true, 
                true
            ));

            return new Response(
                ResponseState.OK,
                "Company user Created!"
            );

        } catch (InvalidUserNameException | InvalidPhoneException | InvalidEmailException ex) {
            return new Response(
                ResponseState.ERROR,
                ex.getMessage()
            );

        }catch (Exception ex) {
            return new Response(
                ResponseState.ERROR,
                "Not Ok"
            );
        }
    }

    public Response getAdministrators() {
        try {
            List<Administrator> administrators = this.administratorService.getAll();
            List<AdministratorDTO> processedAdministrators = new ArrayList<AdministratorDTO>();

            for (Administrator administrator : administrators) {
                processedAdministrators.add(new AdministratorDTO(
                    administrator.getUserId(),
                    administrator.getName(),
                    administrator.getNif(),
                    administrator.getPhone(),
                    administrator.getEmail(),
                    administrator.getIsEnabled()
                ));
            }

            return new Response(
                ResponseState.OK,
                new Gson().toJson(processedAdministrators)
            );

        } catch (Exception e) {
            return new Response(
                ResponseState.ERROR,
                "Can't fetch the users"
            );
        }
    }

    public Response updateAdministrator(UpdateAdministratorDTO body) {
        try {
            Administrator administrator = this.administratorService.getById(body.getUserId());

            administrator.setName(body.getName());
            administrator.setPhone(body.getPhone());
            administrator.setEmail(body.getEmail());

            this.validateAdministratorData.validateAdministrator(administrator);

            this.administratorService.update(administrator);

            return new Response(
                ResponseState.OK,
                "User Updated Successfully!"
            );

        } catch (InvalidUserNameException | InvalidPhoneException | InvalidEmailException ex) {
            return new Response(
                ResponseState.ERROR,
                ex.getMessage()
            );

        } catch (Exception ex) {
            return new Response(
                ResponseState.ERROR,
                ex.getMessage()
            );
        }
    }

}
