package za.flatrock.assessment.demo.models.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.flatrock.assessment.demo.models.daos.Role;
import za.flatrock.assessment.demo.models.enums.RoleEnum;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findRoleById(Long id);

    List<Role> findByNameIn(List<RoleEnum> roleNames);

    Role findRoleByName(RoleEnum roleName);

    Role save(Role role);
}