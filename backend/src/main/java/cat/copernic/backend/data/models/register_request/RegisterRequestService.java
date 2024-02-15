package cat.copernic.backend.data.models.register_request;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface RegisterRequestService {
    
    void save(RegisterRequest registerRequest);

    List<RegisterRequest> getAll();

    RegisterRequest getById(Integer id);

    void update(RegisterRequest registerRequest);

    void deleteById(Integer id);

    RegisterRequest getByNif(String nif);

    RegisterRequest getByEmail(String nif);

}
