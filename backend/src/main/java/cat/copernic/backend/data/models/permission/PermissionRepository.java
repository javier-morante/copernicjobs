package cat.copernic.backend.data.models.permission;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.Modifying;

import cat.copernic.backend.data.enums.UserRole;

public interface PermissionRepository extends JpaRepository<Permission, Integer> {

    List<Permission> findByRole(UserRole role);
 
    List<Permission> findByName(String name);

    @Transactional
    @Modifying
    @Query("UPDATE Permission SET isEnabled = ?1 WHERE name = ?2 AND role = ?3")
    void updateStatusByPermissionAndRole(Boolean status, String permissionName, UserRole userRole);

}
