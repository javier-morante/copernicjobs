package cat.copernic.backend.data.models.incidence;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IncidenceServiceImpl implements IncidenceService {

    @Autowired
    private IncidenceRepository incidenceRepository;

    @Override
    public void save(Incidence incidence) {
        this.incidenceRepository.save(incidence);
    }

    @Override
    public List<Incidence> getAll() {
        return this.incidenceRepository.findAll();
    }

    @Override
    public Incidence getById(Integer id) {
        return this.incidenceRepository.findById(id).orElse(null);
    }

    @Override
    public void update(Incidence incidence) {
        this.save(incidence);
    }

    @Override
    public void deleteById(Integer id) {
        this.incidenceRepository.deleteById(id);
    }
    
}
