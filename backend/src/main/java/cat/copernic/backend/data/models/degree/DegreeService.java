package cat.copernic.backend.data.models.degree;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface DegreeService {

    void save(Degree degree);

    List<Degree> getAll();

    Degree getById(Integer id);

    Degree getByName(String name);

    void update(Degree degree);

    void deleteById(Integer id);

}
