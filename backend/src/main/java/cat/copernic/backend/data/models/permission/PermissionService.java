package cat.copernic.backend.data.models.permission;

import java.util.List;

import org.springframework.stereotype.Service;

import cat.copernic.backend.data.enums.UserRole;

@Service
public interface PermissionService {
    
    void save(Permission permission);

    List<Permission> getAll();

    List<Permission> getByRole(UserRole role);

    List<Permission> getByName(String name);

    Permission getById(Integer id);

    void update(Permission permission);

    void updateStatusByPermissionAndRole(Boolean status, String permissionName, UserRole userRole);

    void deleteById(Integer id);

}
