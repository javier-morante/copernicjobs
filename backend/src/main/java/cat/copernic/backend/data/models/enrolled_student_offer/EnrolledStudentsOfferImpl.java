package cat.copernic.backend.data.models.enrolled_student_offer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnrolledStudentsOfferImpl implements EnrolledStudentsOfferService {
    @Autowired
    private EnrolledStudentsOfferRepository enrolledStudentsOfferRepository;
    
    @Override
    public void save(EnrolledStudentsOffer enrolledStudentsOffer) {
        this.enrolledStudentsOfferRepository.save(enrolledStudentsOffer);
    }

    @Override
    public List<EnrolledStudentsOffer> getAll() {
        return this.enrolledStudentsOfferRepository.findAll();
    }

    @Override
    public EnrolledStudentsOffer getById(EnrolledStudentsOfferId id) {
        return this.enrolledStudentsOfferRepository.findById(id).orElse(null);
    }

    @Override
    public List<EnrolledStudentsOffer> getByUserId(Integer id) {
        return this.enrolledStudentsOfferRepository.getByUserId(id);
    }

    @Override
    public List<EnrolledStudentsOffer> getByOfferId(Integer id) {
        return this.enrolledStudentsOfferRepository.getByOfferId(id);
    }

    @Override
    public void update(EnrolledStudentsOffer enrolledStudentsOffer) {
        this.save(enrolledStudentsOffer);
    }

    @Override
    public void deleteById(EnrolledStudentsOfferId id) {
        this.enrolledStudentsOfferRepository.deleteById(id);
    }
}
