package cat.copernic.backend.data.models.administrator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AministratorServiceImpl implements AdministratorService {

    @Autowired
    private AdministratorRepository administratorRepository;

    @Override
    public void save(Administrator administrator) {
        this.administratorRepository.save(administrator);
    }

    @Override
    public List<Administrator> getAll() {
        return this.administratorRepository.findAll();
    }

    @Override
    public Administrator getById(Integer id) {
        return this.administratorRepository.findById(id).orElse(null);
    }

    @Override
    public void update(Administrator administrator) {
        this.save(administrator);
    }

    @Override
    public void deleteById(Integer id) {
        this.administratorRepository.deleteById(id);
    }
    
}
