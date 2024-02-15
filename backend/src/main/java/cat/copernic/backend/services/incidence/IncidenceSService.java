package cat.copernic.backend.services.incidence;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import cat.copernic.backend.data.dto.incidence.IncidenceDTO;
import cat.copernic.backend.data.dto.incidence.IncidenceNoIdDTO;
import cat.copernic.backend.data.enums.IncidenceStatus;
import cat.copernic.backend.data.enums.UserRole;
import cat.copernic.backend.data.models.Response;
import cat.copernic.backend.data.models.administrator.AdministratorService;
import cat.copernic.backend.data.models.company.Company;
import cat.copernic.backend.data.models.company.CompanyService;
import cat.copernic.backend.data.models.incidence.Incidence;
import cat.copernic.backend.data.models.incidence.IncidenceService;
import cat.copernic.backend.data.models.user.User;
import cat.copernic.backend.data.models.user.UserServiceImpl;
import cat.copernic.backend.services.mailing.EmailService;
import cat.copernic.backend.services.utils.validators.incidence.IncidenceValidator;
import cat.copernic.backend.services.utils.validators.offer_creation.exceptions.InvalidTitleException;
import cat.copernic.backend.services.utils.validators.user_profiles.exceptions.InvalidDescriptionException;

@Service
public class IncidenceSService {

    @Autowired
    IncidenceService incidenceDAO;

    @Autowired
    UserServiceImpl userDAO;

    @Autowired
    private AdministratorService administratorService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private IncidenceValidator incidenceValidator;

    ModelMapper modelMapper = new ModelMapper();

    public Response save(IncidenceNoIdDTO incidenceDTO, String email) {
        try {
            User user = userDAO.getByEmail(email);

            if (user == null)
                return new Response(400, "Error has ocurred");

            Incidence incidence = modelMapper.map(incidenceDTO, Incidence.class);

            this.incidenceValidator.validateIncidence(incidence);

            incidence.setStatus(IncidenceStatus.PENDING);
            incidence.setUser(user);

            incidence.setCreationDate(LocalDate.now().toString());
            incidenceDAO.save(incidence);

            this.emailService.newReportNotification(
                    this.administratorService.getAll(),
                    incidence.getTitle(),
                    incidence.getUser().getEmail());

            return new Response(200, "ok");
        } catch (InvalidDescriptionException | InvalidTitleException e) {
            e.printStackTrace();
            return new Response(400, e.getMessage());

        } catch (Exception e) {
            e.printStackTrace();
            return new Response(500, "Ha ocorrgut un error");
        }
    }

    public Response getAll(String email) {
        User user = userDAO.getByEmail(email);
        if (user == null)
            return new Response(400, "Error has ocurred");

        List<Incidence> repoList = incidenceDAO.getAll().stream()
                .filter(incidence -> user.getUserRole().equals(UserRole.ADMINISTRATOR)
                        || incidence.getUser().bequals(user))
                .collect(Collectors.toList());

        List<IncidenceDTO> incidenceDTOlList = repoList.stream().map((incidence) -> {
            IncidenceDTO tmp = modelMapper.map(incidence, IncidenceDTO.class);
            tmp.setUserName(incidence.getUser().getName());
            tmp.setUserRole(incidence.getUser().getUserRole());
            return tmp;
        }).collect(Collectors.toList());

        return new Response(200, new Gson().toJson(incidenceDTOlList));
    }

    public Response update(IncidenceDTO incidenceDTO, String email) {
        try {
            Incidence incidenceToCheck = this.modelMapper.map(incidenceDTO, Incidence.class);

            this.incidenceValidator.validateIncidence(incidenceToCheck);

            Incidence incidence = this.incidenceDAO.getById(incidenceToCheck.getId());

            if (incidence == null)
                return new Response(400, "Error has ocurred incidence");

            if (!incidence.equals(incidenceToCheck))
                return new Response(400, "Error has ocurred not equals");

            incidenceToCheck.setUser(incidence.getUser());

            this.incidenceDAO.update(incidenceToCheck);

            Company company = this.companyService.getById(incidence.getUser().getUserId());

            if (company != null && company.isResolvedReportNotification()) {
                this.emailService.solvedReportNotification(company, incidence.getTitle());
            }

            return new Response(200, "ok");
        } catch (InvalidDescriptionException | InvalidTitleException e) {
            return new Response(400, e.getMessage());
        } catch (Exception e) {
            return new Response(500, "Ha ocorrgut un error");
        }
    }

}
