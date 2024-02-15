package cat.copernic.backend.data.models.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void save(User user) {
        this.userRepository.save(user);
    }

    @Override
    public List<User> getAll() {
        return this.userRepository.findAll();
    }

    @Override
    public User getById(Integer id) {
        return this.userRepository.findById(id).orElse(null);
    }

    
    @Override
    public User getByEmail(String email) {
        return this.userRepository.findByEmail(email).orElse(null);
    }

    @Override
    public void update(User user) {
        this.userRepository.save(user);
    }

    @Override
    public void deleteById(User id) {
        return;
    }

    @Override
    public User getByNif(String nif) {
        return this.userRepository.findByNif(nif).orElse(null);
    }

}
