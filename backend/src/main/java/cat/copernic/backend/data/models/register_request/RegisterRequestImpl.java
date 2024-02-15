package cat.copernic.backend.data.models.register_request;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterRequestImpl implements RegisterRequestService {

    @Autowired
    private RegisterRequestRepository registerRequestRepository;

    @Override
    public void save(RegisterRequest registerRequest) {
        this.registerRequestRepository.save(registerRequest);
    }

    @Override
    public List<RegisterRequest> getAll() {
        return this.registerRequestRepository.findAll();
    }

    @Override
    public RegisterRequest getById(Integer id) {
        return this.registerRequestRepository.findById(id).orElse(null);
    }

    @Override
    public void update(RegisterRequest registerRequest) {
        this.save(registerRequest);
    }

    @Override
    public void deleteById(Integer id) {
        this.registerRequestRepository.deleteById(id);
    }

    @Override
    public RegisterRequest getByNif(String nif) {
        return this.registerRequestRepository.findByNif(nif).orElse(null);
    }

    @Override
    public RegisterRequest getByEmail(String email) {
        return this.registerRequestRepository.findByEmail(email).orElse(null);
    }    
}
