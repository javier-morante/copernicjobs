package cat.copernic.backend.data.models.degree;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DegreeServiceImpl implements DegreeService {

    @Autowired
    private DegreeRepository degreeRepository;

    @Override
    public void save(Degree degree) {
        this.degreeRepository.save(degree);
    }

    @Override
    public List<Degree> getAll() {
        return this.degreeRepository.findAll();
    }

    @Override
    public Degree getById(Integer id) {
        return this.degreeRepository.findById(id).orElse(null);
    }

    @Override
    public Degree getByName(String name) {
        return this.degreeRepository.findByName(name).orElse(null);
    }

    @Override
    public void update(Degree degree) {
        this.save(degree);
    }

    @Override
    public void deleteById(Integer id) {
        this.degreeRepository.deleteById(id);
    }

    
}
