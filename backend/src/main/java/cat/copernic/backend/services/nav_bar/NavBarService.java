package cat.copernic.backend.services.nav_bar;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import cat.copernic.backend.data.dto.navBar.PermissionsDTO;
import cat.copernic.backend.data.models.permission.Permission;
import cat.copernic.backend.data.models.permission.PermissionService;
import cat.copernic.backend.data.models.user.User;
import cat.copernic.backend.data.models.user.UserService;
import cat.copernic.backend.data.responses.Response;
import cat.copernic.backend.data.responses.ResponseState;

@Service
public class NavBarService {

    @Autowired
    private UserService userService;

    @Autowired
    private PermissionService permissionService;

    private final ModelMapper modelMapper = new ModelMapper();

    public Response getUserPermissions(Authentication auth) {
        try {
            User user = this.userService.getByEmail(auth.getName());

            List<Permission> roles = this.permissionService.getByRole(user.getUserRole());
            List<PermissionsDTO> rolesResult = new ArrayList<>();
    
            for (Permission permission : roles) {
                rolesResult.add(
                    this.modelMapper.map(permission, PermissionsDTO.class)
                );
            }
    
            return new Response(
                ResponseState.OK,
                new Gson().toJson(rolesResult)
            );

        } catch (Exception ex) {
            ex.printStackTrace();

            return new Response(
                ResponseState.ERROR,
                ex.getMessage()
            );
        
        }
    }

    public Response getModulePermissions(String body) {
        try {
            List<Permission> roles = this.permissionService.getByName(body);
            List<PermissionsDTO> rolesResult = new ArrayList<>();

            for (Permission permission : roles) {
                rolesResult.add(
                    this.modelMapper.map(permission, PermissionsDTO.class)
                );
            }

            return new Response(
                ResponseState.OK,
                new Gson().toJson(rolesResult)
            );

        } catch (Exception ex) {
            ex.printStackTrace();
            
            return new Response(
                ResponseState.ERROR,
                ex.getMessage()
            );
        
        }
    }
}
