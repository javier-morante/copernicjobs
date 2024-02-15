package cat.copernic.backend.services.reports;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import cat.copernic.backend.data.dto.reports.UserQuantityReportsDTO;
import cat.copernic.backend.data.models.administrator.AdministratorService;
import cat.copernic.backend.data.models.company.CompanyService;
import cat.copernic.backend.data.models.enrolled_student_offer.EnrolledStudentsOffer;
import cat.copernic.backend.data.models.enrolled_student_offer.EnrolledStudentsOfferService;
import cat.copernic.backend.data.models.offer.Offer;
import cat.copernic.backend.data.models.offer.OfferService;
import cat.copernic.backend.data.models.student.Student;
import cat.copernic.backend.data.models.student.StudentService;
import cat.copernic.backend.data.responses.Response;
import cat.copernic.backend.data.responses.ResponseState;

@Service
public class ReportsService {
    
    @Autowired
    private StudentService studentService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private AdministratorService administratorService;

    @Autowired
    private EnrolledStudentsOfferService enrolledStudentsOfferService;

    @Autowired
    private OfferService offerService;

    public Response getUsersQuantity() {
        try {
            UserQuantityReportsDTO userQuantityReports = new UserQuantityReportsDTO(
                this.studentService.getAll().size(),
                this.companyService.getAll().size(),
                this.administratorService.getAll().size()
            );
    
            return new Response(
                ResponseState.OK,
                new Gson().toJson(userQuantityReports)
            );   
        } catch (Exception ex) {
            return new Response(
                ResponseState.ERROR,
                ex.getMessage()
            );
        }
    }

    public Response getEnrolledStudentQuantity() {
        try {
            List<EnrolledStudentsOffer> enrollmentList = this.enrolledStudentsOfferService.getAll();

            Map<Integer, Integer> monthEnrollments = new HashMap<>();

            for (EnrolledStudentsOffer enrollment : enrollmentList) {
                Integer month = enrollment.getEnrollmentDate().getMonth();

                if(!monthEnrollments.keySet().contains(month)) {
                    monthEnrollments.put(month, 1);
                    continue;
                }

                monthEnrollments.put(month, monthEnrollments.get(month)+1);
            }

            return new Response(
                ResponseState.OK,
                new Gson().toJson(monthEnrollments)
            );

        } catch (Exception ex) {
            return new Response(
                ResponseState.ERROR,
                ex.getMessage()
            );
        }
    }

    public Response getOfferPublishments() {
        try {
            List<Offer> offersList = this.offerService.getAll();
            Map<Integer, Integer> offerPublishments = new HashMap<>();

            for (Offer offer : offersList) {
                Integer month = offer.getCreationDate().getMonth();

                if(!offerPublishments.keySet().contains(month)) {
                    offerPublishments.put(month, 1);
                    continue;
                }

                offerPublishments.put(month, offerPublishments.get(month)+1);
            }

            return new Response(
                ResponseState.OK,
                new Gson().toJson(offerPublishments)
            );

        } catch (Exception ex) {
            ex.printStackTrace();
            return new Response(
                ResponseState.ERROR,
                ex.getMessage()
            );
        }
    }


    public Response getUserTraffic() {
        try {
            List<Student> students = this.studentService.getAll();

            Map<Integer, Integer> monthlyUsers = new HashMap<>();
    
            for (Student student : students) {

                if(student.getLastLogin() == null) {
                    continue;
                }
                
                Integer month = student.getLastLogin().getMonth();
    
                if(!monthlyUsers.keySet().contains(month)) {
                    monthlyUsers.put(month, 1);
                    continue;
                }
    
                monthlyUsers.put(month, monthlyUsers.get(month)+1);
            }
    
            return new Response(
                ResponseState.OK,
                new Gson().toJson(monthlyUsers)
            );
            
        } catch (Exception ex) {
            return new Response(
                ResponseState.ERROR,
                ex.getMessage()
            );
        }
    }

}
