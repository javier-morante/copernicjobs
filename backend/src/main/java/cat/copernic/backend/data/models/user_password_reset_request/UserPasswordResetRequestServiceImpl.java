package cat.copernic.backend.data.models.user_password_reset_request;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserPasswordResetRequestServiceImpl implements UserPasswordResetRequestService {

    @Autowired
    private UserPasswordResetRequestRepository userPasswordResetRequestRepository;

    @Override
    public void save(UserPasswordResetRequest passwordResetR) {
        this.userPasswordResetRequestRepository.save(passwordResetR);
    }

    @Override
    public List<UserPasswordResetRequest> getUserPasswordResetRequests() {
       return this.userPasswordResetRequestRepository.findAll();
    }

    @Override
    public UserPasswordResetRequest getUserPasswordResetRequestById(Integer id) {
        return this.userPasswordResetRequestRepository.findById(id).orElse(null);
    }

    @Override
    public UserPasswordResetRequest getUserPasswordRegisterRequestByEmail(String email) {
        return this.userPasswordResetRequestRepository.findByEmail(email).orElse(null);
    }

    @Override
    public UserPasswordResetRequest getUserPasswordRegisterRequestByEmailAndCode(String email, String code) {
        return this.userPasswordResetRequestRepository.findByEmailAndCode(email, code).orElse(null);
    }

    @Override
    public void updateUserPasswordResetRequest(UserPasswordResetRequest passwordResetR) {
        this.userPasswordResetRequestRepository.save(passwordResetR);
    }

    @Override
    public void deleteUserPasswordResetRequestById(Integer id) {
        this.userPasswordResetRequestRepository.deleteById(id);
    }

}
