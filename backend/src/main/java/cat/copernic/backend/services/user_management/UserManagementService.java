package cat.copernic.backend.services.user_management;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import cat.copernic.backend.data.dto.user_management.UpdateUserPasswordDTO;
import cat.copernic.backend.data.models.administrator.AdministratorService;
import cat.copernic.backend.data.models.company.CompanyService;
import cat.copernic.backend.data.models.student.StudentService;
import cat.copernic.backend.data.models.user.User;
import cat.copernic.backend.data.models.user.UserService;
import cat.copernic.backend.data.responses.Response;
import cat.copernic.backend.data.responses.ResponseState;

@Service
public class UserManagementService {
    
    @Autowired
    private UserService userService;


    @Autowired
    private StudentService studentService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private AdministratorService administratorService;


    @Autowired
    private PasswordEncoder passwordEncoder;


    // Deletes a user by id
    public Response deleteUser(Authentication auth, Integer userId) { 
        try {
            if(this.userService.getByEmail(auth.getName()).getUserId() == userId) {
                return new Response(
                    ResponseState.ERROR,
                    "No es pot bloquejar l'usuari"
                );
            }

            switch (this.userService.getById(userId).getUserRole()) {
                case STUDENT:
                    this.studentService.deleteById(userId);
                    break;
                case COMPANY:
                    this.companyService.deleteById(userId);
                    break;
                case ADMINISTRATOR:
                    this.administratorService.deleteById(userId);
                    break;
            }

            return new Response(
                ResponseState.OK,
                "User Successfully Deleted!"
            );

        } catch (Exception ex) {
            System.out.printf(
                "Delete User Error => %s\n", ex.getMessage()
            );

            return new Response(
                ResponseState.ERROR,
                "Error, user not deleted!"
            );
        }
    }

    // Locks / Disables a user by id
    public Response lockUser(Authentication auth, Integer userId) {
        try {
            if(this.userService.getByEmail(auth.getName()).getUserId() == userId) {
                return new Response(
                    ResponseState.ERROR,
                    "No es pot bloquejar l'usuari"
                );
            }

            User user = this.userService.getById(userId);
            user.setIsEnabled(!user.getIsEnabled());

            this.userService.update(user);

            return new Response(
                ResponseState.OK,
                (user.getIsEnabled())
                    ? "User Unlocked Successfully!"
                    : "User Locked Successfully"
            );

        } catch (Exception ex) {
            System.out.printf(
                "Lock User Error => %s\n", ex.getMessage()
            );

            return new Response(
                ResponseState.ERROR,
                "Error"
            );
        }
    }

    // Updates the user Password
    public Response updateUserPassword(UpdateUserPasswordDTO body) {
        try {
            User user = this.userService.getById(body.getUserId());

            user.setPassword(
                this.passwordEncoder.encode(body.getPassword())
            );

            this.userService.update(user);

            return new Response(
                ResponseState.OK,
                "Password Updated Successfully!"
            );

        } catch (Exception ex) {
            System.out.printf(
                "User Password Update Error => %s\n", ex.getMessage()
            );

            return new Response(
                ResponseState.ERROR,
                "Error, user password not updated!"
            );
        }
    }

}
