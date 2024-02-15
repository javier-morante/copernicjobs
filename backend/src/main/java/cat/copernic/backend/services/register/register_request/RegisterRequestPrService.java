package cat.copernic.backend.services.register.register_request;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import cat.copernic.backend.data.dto.register.RegisterRequestDTO;
import cat.copernic.backend.data.dto.register.RegisterRequestDTONoPass;
import cat.copernic.backend.data.enums.RequestStatus;
import cat.copernic.backend.data.models.Response;
import cat.copernic.backend.data.models.administrator.AdministratorService;
import cat.copernic.backend.data.models.register_request.RegisterRequest;
import cat.copernic.backend.data.models.register_request.RegisterRequestService;
import cat.copernic.backend.services.mailing.EmailService;
import cat.copernic.backend.services.utils.validators.register_request.RegisterRequestValidator;
import cat.copernic.backend.services.utils.validators.user_profiles.exceptions.InvalidCIFException;
import cat.copernic.backend.services.utils.validators.user_profiles.exceptions.InvalidEmailException;
import cat.copernic.backend.services.utils.validators.user_profiles.exceptions.InvalidPhoneException;
import cat.copernic.backend.services.utils.validators.user_profiles.exceptions.InvalidUserNameException;

@Service
public class RegisterRequestPrService {

    @Autowired
    private PasswordEncoder bpe;

    @Autowired
    private RegisterRequestService registerRequestDAO;

    @Autowired
    private AdministratorService administratorService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private RegisterRequestValidator validateRR;

    private final ModelMapper modelMapper = new ModelMapper();

    public Response save(RegisterRequestDTO registerRequestDTO) {
        try {

            RegisterRequest rr = modelMapper.map(registerRequestDTO, RegisterRequest.class);

            this.validateRR.validateRegiaterRequest(rr);

            this.emailService.newAccessRequestNotification(
                    this.administratorService.getAll(),
                    registerRequestDTO.getEmail());

            rr.setStatus(RequestStatus.WAITING);
            crypt(rr);
            registerRequestDAO.save(rr);
            return new Response(201, "Regester created");
        } catch (InvalidUserNameException | InvalidEmailException | InvalidPhoneException | InvalidCIFException e) {
            return new Response(400,e.getMessage());
        }catch(Exception e){
            return new Response(500, "Ha ocorrgut un error");
        }

    }

    public RegisterRequest crypt(RegisterRequest rr) {
        String pass = this.bpe.encode(rr.getPassword());
        rr.setPassword(pass);
        return rr;
    }

    public Response getRegisterRequest() {

        List<RegisterRequestDTONoPass> listDTO = this.registerRequestDAO.getAll().stream()
                .map((e) -> modelMapper.map(e, RegisterRequestDTONoPass.class)).collect(Collectors.toList());

        if (listDTO == null) {
            return new Response(400, "data not exit");
        }

        return new Response(200, new Gson().toJson(listDTO));
    }

}
