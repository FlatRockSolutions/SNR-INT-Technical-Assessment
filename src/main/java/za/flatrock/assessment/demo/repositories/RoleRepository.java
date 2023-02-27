package za.flatrock.assessment.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.flatrock.assessment.demo.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}
