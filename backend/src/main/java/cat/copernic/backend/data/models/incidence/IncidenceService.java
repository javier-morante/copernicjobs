package cat.copernic.backend.data.models.incidence;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface IncidenceService {
 
    void save(Incidence incidence);

    List<Incidence> getAll();

    Incidence getById(Integer id);

    void update(Incidence incidence);

    void deleteById(Integer id);

}
