package cat.copernic.backend.data.models.company;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public void save(Company company) {
        this.companyRepository.save(company);
    }

    @Override
    public List<Company> getAll() {
        return this.companyRepository.findAll();
    }

    @Override
    public Company getById(Integer id) {
        return this.companyRepository.findById(id).orElse(null);
    }

    @Override
    public Company getByNif(String nif) {
        return this.companyRepository.findByNif(nif).orElse(null);
    }

    @Override
    public void update(Company administrator) {
        this.save(administrator);
    }

    @Override
    public void deleteById(Integer id) {
        this.companyRepository.deleteById(id);
    }

}
