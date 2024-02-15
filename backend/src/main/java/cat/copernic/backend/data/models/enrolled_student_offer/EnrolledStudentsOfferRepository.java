package cat.copernic.backend.data.models.enrolled_student_offer;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EnrolledStudentsOfferRepository extends JpaRepository<EnrolledStudentsOffer, EnrolledStudentsOfferId>{
    @Query("SELECT e FROM EnrolledStudentsOffer e WHERE e.student.id = :userId")
    List<EnrolledStudentsOffer> getByUserId(Integer userId);

    @Query("SELECT e FROM EnrolledStudentsOffer e WHERE e.offer.id = :offerId")
    List<EnrolledStudentsOffer> getByOfferId(Integer offerId);
}
