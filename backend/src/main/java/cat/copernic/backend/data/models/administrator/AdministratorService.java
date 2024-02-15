package cat.copernic.backend.data.models.administrator;

import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface AdministratorService {
    
    void save(Administrator administrator);

    List<Administrator> getAll();

    Administrator getById(Integer id);

    void update(Administrator administrator);

    void deleteById(Integer id);

}
