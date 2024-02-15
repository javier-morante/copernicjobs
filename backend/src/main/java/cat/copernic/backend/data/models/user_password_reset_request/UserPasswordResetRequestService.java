package cat.copernic.backend.data.models.user_password_reset_request;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface UserPasswordResetRequestService {

    void save(UserPasswordResetRequest passwordResetR);

    List<UserPasswordResetRequest> getUserPasswordResetRequests();

    UserPasswordResetRequest getUserPasswordResetRequestById(Integer id);

    UserPasswordResetRequest getUserPasswordRegisterRequestByEmail(String email);

    UserPasswordResetRequest getUserPasswordRegisterRequestByEmailAndCode(String email, String code);

    void updateUserPasswordResetRequest(UserPasswordResetRequest passwordResetR);

    void deleteUserPasswordResetRequestById(Integer id);
}
