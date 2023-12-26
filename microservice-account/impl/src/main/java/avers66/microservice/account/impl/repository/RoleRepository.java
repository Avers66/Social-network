package avers66.microservice.account.impl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import avers66.microservice.account.domain.model.Role;

import java.util.List;
import java.util.UUID;
@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {
    @Query("select r from Role r where r.role like 'ROLE_USER'")
    List<Role> getUserRoles();

}
