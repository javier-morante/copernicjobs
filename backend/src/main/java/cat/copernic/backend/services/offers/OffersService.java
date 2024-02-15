package cat.copernic.backend.services.offers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import cat.copernic.backend.data.dto.offers.CreateOfferDTO;
import cat.copernic.backend.data.dto.offers.OffersDTO;
import cat.copernic.backend.data.dto.student_profesional_profile.StudentProfesionalProfileDTO;
import cat.copernic.backend.data.dto.user_management.StudentDTO;
import cat.copernic.backend.data.models.company.Company;
import cat.copernic.backend.data.models.degree.Degree;
import cat.copernic.backend.data.models.degree.DegreeService;
import cat.copernic.backend.data.models.offer.Offer;
import cat.copernic.backend.data.models.offer.OfferService;
import cat.copernic.backend.data.models.user.User;
import cat.copernic.backend.data.models.user.UserService;
import cat.copernic.backend.data.responses.Response;
import cat.copernic.backend.data.responses.ResponseState;
import cat.copernic.backend.services.mailing.EmailService;
import cat.copernic.backend.services.profesional_profile.ProfesionalProfileService;
import cat.copernic.backend.services.utils.validators.offer_creation.ValidateOfferData;
import cat.copernic.backend.services.utils.validators.offer_creation.exceptions.InvalidDescriptionException;
import cat.copernic.backend.services.utils.validators.offer_creation.exceptions.InvalidLocationException;
import cat.copernic.backend.services.utils.validators.offer_creation.exceptions.InvalidSalaryIntervalException;
import cat.copernic.backend.services.utils.validators.offer_creation.exceptions.InvalidTitleException;

import cat.copernic.backend.data.models.enrolled_student_offer.EnrolledStudentsOffer;
import cat.copernic.backend.data.models.enrolled_student_offer.EnrolledStudentsOfferId;
import cat.copernic.backend.data.models.enrolled_student_offer.EnrolledStudentsOfferImpl;
import cat.copernic.backend.data.models.student.Student;
import cat.copernic.backend.data.models.student.StudentService;
import cat.copernic.backend.data.models.student_professional_profile.StudentProfessionalProfile;
import cat.copernic.backend.data.models.student_professional_profile.StudentProfessionalProfileServiceImpl;

import java.sql.Date;

@Service
public class OffersService {
    @Autowired
    private OfferService offerService;

    @Autowired
    private UserService userService; 

    @Autowired
    private DegreeService degreeService;

    @Autowired
    private ValidateOfferData validateOfferData;

    @Autowired
    private StudentService studentService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private StudentProfessionalProfileServiceImpl studentProfessionalProfileServiceImpl;

    @Autowired
    private EnrolledStudentsOfferImpl enrolledStudentsOfferDao;

    private ModelMapper modelMapper = new ModelMapper();

    public Response getAllOffers(Authentication auth) {
        List<OffersDTO> offers = mapToOffersDTO(this.offerService.getAll());

        return new Response(
            ResponseState.OK,
            new Gson().toJson(offers)
        );
    }

    public Response subscribeStudent(Authentication auth, Integer id) {
        Offer offer = offerService.getById(id);
        Student student = this.studentService.getByEmail(auth.getName());
        StudentProfessionalProfile studentProfessionalProfile = studentProfessionalProfileServiceImpl.getByStudent(student);
        
        if (!studentProfessionalProfile.getCurriculumPath().isBlank()) {
            enrolledStudentsOfferDao.save(new EnrolledStudentsOffer(
                new EnrolledStudentsOfferId(((Student)student).getUserId(), id),
                (Student)student,
                offer,
                new Date(System.currentTimeMillis()))
            );
    
            // Send Email
            this.emailService.newInscriptionNotificationEmail(
                student,
                offer.getTitle()
            );
    
            return new Response(ResponseState.OK, "Student subscribed succesfully");
        } else {
            return new Response(ResponseState.ERROR, "Can't subscribe student because it doesn't have a curriculum");
        }
    }

    public Response unsubscribeStudent(Authentication auth, Integer id) {
        User student = this.userService.getByEmail(auth.getName());
        Offer offer = this.offerService.getById(id);
        enrolledStudentsOfferDao.deleteById(new EnrolledStudentsOfferId(((Student)student).getUserId(), id));
        
        this.emailService.unsubscriptionNotification(
            student.getEmail(), 
            offer.getTitle(), 
            offer.getUser().getEmail());

        return new Response(ResponseState.OK, "Student unsubscribed successfully");
    }

    public Response getSubscribedOffers(Authentication auth) {
        List<EnrolledStudentsOffer> enrolledStudentsOffers = enrolledStudentsOfferDao.getByUserId(userService.getByEmail(auth.getName()).getUserId());
        List<Offer> offers = new ArrayList<>();
        
        for (EnrolledStudentsOffer offer : enrolledStudentsOffers) {
            offers.add(offer.getOffer());
        }
        
        List<OffersDTO> DTOoffers = mapToOffersDTO(offers); 
        return new Response(ResponseState.OK, new Gson().toJson(DTOoffers));
    }

    public Response getSubscribedStudents(Integer id) {
        List<EnrolledStudentsOffer> offers = enrolledStudentsOfferDao.getByOfferId(id);
        List<Student> enrolledStudents = new ArrayList<>();

        for (EnrolledStudentsOffer offer : offers) {
            enrolledStudents.add(offer.getStudent());
        }

        List<StudentDTO> studentsDTO = enrolledStudents.stream().map((student) -> {
            StudentDTO studentDTO = modelMapper.map(student, StudentDTO.class);
            return studentDTO;

        }).collect(Collectors.toList());
        
        return new Response(ResponseState.OK, new Gson().toJson(studentsDTO));
    }

    private List<OffersDTO> mapToOffersDTO(List<Offer> offers) {
        return offers.stream().map((offer) -> {
            OffersDTO offersDTO = modelMapper.map(offer, OffersDTO.class);
            return this.validateOfferOwnerRole(offer.getUser(), offersDTO);

        }).collect(Collectors.toList());
    }

    // Verifies the rol of the user, and in case that the user is a company, 
    // then sets the name, iconPath and nif of that company to the offer
    // if the user is not a company, then sets the company name as the userEmail and
    // leaves the other fields as default
    private OffersDTO validateOfferOwnerRole(User user, OffersDTO body) {
        if( user instanceof Company ) {

            Company company = (Company)user;
        
            body.setCompanyName(company.getName());
            body.setCompanyImage(company.getIconPath());
            body.setCompanyNif(company.getNif());

            return body;
        }

        body.setCompanyName(user.getEmail());
        return body;
    }

    public Response createOffer(Authentication auth, CreateOfferDTO body) {
        try {
            Offer newOffer = modelMapper.map(body, Offer.class);

            Degree offerDegree = this.degreeService.getByName(body.getDegree());

            newOffer.setUser(this.userService.getByEmail(auth.getName()));
            newOffer.setDegree(offerDegree);
            newOffer.setCreationDate(new Date(System.currentTimeMillis()));
            newOffer.setIsEnabled(true);
            
            this.validateOfferData.validateOffer(newOffer);

            // Send Email
            this.emailService.newOfferNotificationEmail(
                this.studentService.getByNewOfferNotification(), 
                newOffer.getTitle(),
                newOffer.getUser().getEmail()
            );

            
            this.offerService.save(newOffer);

            return new Response(
                ResponseState.OK,
                "New Offer Created!"
            );

        } catch (InvalidTitleException | InvalidLocationException | 
            InvalidDescriptionException | InvalidSalaryIntervalException ex){
            
            return new Response(
                ResponseState.ERROR,
                ex.getMessage()
            );

        } catch (Exception ex) {
            ex.printStackTrace();

            return new Response(
                ResponseState.ERROR,
                "Error, Offer not created"
            );
        }
    }


    public Response updateOffer(Authentication auth, CreateOfferDTO body) {
        try {
            Offer offer = this.offerService.getById(body.getId());
            User user = this.userService.getById(offer.getUser().getUserId());

            offer.setTitle(body.getTitle());
            offer.setLocation(body.getLocation());
            offer.setShortDescription(body.getShortDescription());
            offer.setRequirementsDescription(body.getRequirementsDescription());
            offer.setFunctionsDescription(body.getFunctionsDescription());
            offer.setTechnologiesDescription(body.getTechnologiesDescription());
            offer.setUrgency(body.getUrgency());
            offer.setWorkday(body.getWorkday());
            offer.setDegree(this.degreeService.getByName(body.getDegree()));
            offer.setSalaryInterval(body.getSalaryInterval());
            offer.setVacancies(body.getVacancies());

            this.validateOfferData.validateOffer(offer);

            this.offerService.update(offer);

            return new Response(
                ResponseState.OK,
                "Offer Updated!"
            );

        } catch (InvalidTitleException | InvalidLocationException | 
            InvalidDescriptionException | InvalidSalaryIntervalException ex){
            ex.printStackTrace();
            return new Response(
                ResponseState.ERROR,
                ex.getMessage()
            );

        } catch (Exception ex) {
            ex.printStackTrace();
            return new Response(
                ResponseState.ERROR,
                "Offer can't be updated!"
            );
        }
    }

    public Response disableOffer(Authentication auth, Integer offerId) {
        try {
            Offer offer = this.offerService.getById(offerId);
           
            offer.setIsEnabled(!offer.getIsEnabled());

            this.offerService.update(offer);

            return new Response(
                ResponseState.OK,
                "Estatus de l'oferta actualitzat"
            );


        } catch (Exception ex) {
            return new Response(
                ResponseState.ERROR,
                "Error actualitzant l'estatus de la oferta"
            );
        }
    }

}
