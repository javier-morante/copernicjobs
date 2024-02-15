package cat.copernic.backend.data.models.enrolled_student_offer;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface EnrolledStudentsOfferService {
    void save(EnrolledStudentsOffer enrolledStudentsOffer);

    List<EnrolledStudentsOffer> getAll();

    EnrolledStudentsOffer getById(EnrolledStudentsOfferId id);
    
    List<EnrolledStudentsOffer> getByUserId(Integer id);

    List<EnrolledStudentsOffer> getByOfferId(Integer id);

    void update (EnrolledStudentsOffer enrolledStudentsOffer);

    void deleteById(EnrolledStudentsOfferId id);
}
