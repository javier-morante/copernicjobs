package cat.copernic.backend.services.register.validate_register;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cat.copernic.backend.data.dto.register.RegisterRequestDTONoPass;
import cat.copernic.backend.data.models.Response;
import cat.copernic.backend.data.models.company.Company;
import cat.copernic.backend.data.models.company.CompanyService;
import cat.copernic.backend.data.models.register_request.RegisterRequest;
import cat.copernic.backend.data.models.register_request.RegisterRequestService;
import cat.copernic.backend.services.mailing.EmailService;
import cat.copernic.backend.services.utils.validators.register_request.RegisterRequestValidator;
import cat.copernic.backend.services.utils.validators.user_profiles.exceptions.InvalidCIFException;
import cat.copernic.backend.services.utils.validators.user_profiles.exceptions.InvalidEmailException;
import cat.copernic.backend.services.utils.validators.user_profiles.exceptions.InvalidPhoneException;
import cat.copernic.backend.services.utils.validators.user_profiles.exceptions.InvalidUserNameException;

@Service
public class ValidateRequestService {

    @Autowired
    private RegisterRequestService registerRequestDAO;

    @Autowired
    private CompanyService companyDAO;

    @Autowired
    private EmailService emailService;

    @Autowired
    private RegisterRequestValidator validateRR;

    public Response validateRequest(RegisterRequestDTONoPass registerRequestDTONoPass) {
        try {
            this.validateRR.validateRegiaterRequestDTONoPass(registerRequestDTONoPass);

            RegisterRequest rr = getRegisterRequest(registerRequestDTONoPass.getNif(),
                    registerRequestDTONoPass.getEmail());

            if (rr == null) {
                return new Response(400, "error has ocurred");
            }

            this.companyDAO.save(new Company(rr.getCompanyName(), rr.getEmail(), rr.getNif(), rr.getContactPhone(),
                    rr.getPassword(), null, true, "", "", "", false));

            this.registerRequestDAO.deleteById(rr.getId());

            this.emailService.accessNotificationStatus(
                    registerRequestDTONoPass.getEmail(),
                    "acceptada");

            return new Response(200, "ok");
        } catch (InvalidUserNameException | InvalidEmailException | InvalidPhoneException | InvalidCIFException me) {
            return new Response(400, me.getMessage());
        } catch (Exception e) {
            return new Response(500, "Ha ocerregut un error");
        }
    }

    public Response declineRequest(RegisterRequestDTONoPass registerRequestDTONoPass) {
        try {

            RegisterRequest rr = this.registerRequestDAO.getByNif(registerRequestDTONoPass.getNif());

            this.validateRR.validateRegiaterRequest(rr);


            this.validateRR.validateRegiaterRequest(rr);

            this.registerRequestDAO.deleteById(rr.getId());

            this.emailService.accessNotificationStatus(
                    registerRequestDTONoPass.getEmail(),
                    "rebutjada");

            return new Response(200, "ok");

        } catch (InvalidUserNameException | InvalidEmailException | InvalidPhoneException | InvalidCIFException me) {
            return new Response(400, me.getMessage());
        } catch (Exception e) {
            return new Response(500, "Ha ocerregut un error");
        }
    }

    private RegisterRequest getRegisterRequest(String nif, String email) {

        RegisterRequest registerRequest1 = registerRequestDAO.getByNif(nif);

        RegisterRequest registerRequest2 = registerRequestDAO.getByEmail(email);

        if (registerRequest1 != null && registerRequest2 != null) {

            if (registerRequest1.equals(registerRequest2)) {

                return registerRequest1;
            }

        }

        return null;

    }

}
