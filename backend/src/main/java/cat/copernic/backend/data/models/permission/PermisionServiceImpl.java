package cat.copernic.backend.data.models.permission;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cat.copernic.backend.data.enums.UserRole;

@Service
public class PermisionServiceImpl implements PermissionService{

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public void save(Permission permission) {
        this.permissionRepository.save(permission);
    }

    @Override
    public List<Permission> getAll() {
        return this.permissionRepository.findAll();
    }

    @Override
    public List<Permission> getByRole(UserRole role) {
        return this.permissionRepository.findByRole(role);
    }

    @Override
    public List<Permission> getByName(String name) {
        return this.permissionRepository.findByName(name);
    }

    @Override
    public Permission getById(Integer id) {
        return this.permissionRepository.findById(id).orElse(null);
    }

    @Override
    public void update(Permission permission) {
        this.save(permission);
    }

    @Override
    public void updateStatusByPermissionAndRole(Boolean status, String permissionName, UserRole userRole) {
        this.permissionRepository.updateStatusByPermissionAndRole(status, permissionName, userRole);
    }

    @Override
    public void deleteById(Integer id) {
        this.permissionRepository.deleteById(id);
    }

}
