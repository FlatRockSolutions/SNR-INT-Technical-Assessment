package za.flatrock.assessment.demo.models.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.flatrock.assessment.demo.models.daos.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByUniqueUserId(String id);

    User findUserByFirstNameAndLastNameAndCellNumber(String firstName, String lastName, String cellNumber);

    User save(User user);
}
