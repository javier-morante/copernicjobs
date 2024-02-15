package cat.copernic.backend.data.models.company;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface CompanyService {
    
    void save(Company company);

    List<Company> getAll();

    Company getById(Integer id);

    Company getByNif(String nif);

    void update(Company administrator);

    void deleteById(Integer id);

}