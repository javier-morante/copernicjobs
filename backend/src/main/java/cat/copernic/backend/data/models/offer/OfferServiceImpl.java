package cat.copernic.backend.data.models.offer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OfferServiceImpl implements OfferService {

    @Autowired
    private OfferRepository offerRepository;

    @Override
    public void save(Offer offer) {
        this.offerRepository.save(offer);
    }

    @Override
    public List<Offer> getAll() {
        return this.offerRepository.findAll();
    }

    @Override
    public Offer getById(Integer id) {
        Offer off = this.offerRepository.findById(id).orElse(null);
        return off;
    }

    @Override
    public void update(Offer offer) {
        this.save(offer);
    }

    @Override
    public void deleteById(Integer id) {
        this.offerRepository.deleteById(id);
    }
    
}
