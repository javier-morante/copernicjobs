package cat.copernic.backend.data.models.offer;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface OfferService {

    void save(Offer offer);

    List<Offer> getAll();

    Offer getById(Integer id);

    void update(Offer offer);

    void deleteById(Integer id);
    
}
