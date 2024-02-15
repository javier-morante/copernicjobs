package cat.copernic.backend.data.models.user;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface UserService {
    
    void save(User user);

    List<User> getAll();

    User getById(Integer id);

    User getByEmail(String email);

    void update(User user);

    void deleteById(User id);

    User getByNif(String nif);

}
