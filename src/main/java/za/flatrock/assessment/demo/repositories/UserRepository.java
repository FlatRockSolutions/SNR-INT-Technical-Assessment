package za.flatrock.assessment.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import za.flatrock.assessment.demo.entities.User;
import za.flatrock.assessment.demo.entities.UserId;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, UserId> {

    @Query(value = "select * from user_table where UPPER(name)=:name and UPPER(surname)=:surname and cell_phone_number=:cellPhoneNumber", nativeQuery = true)
    Optional<User> findByUserId(String name, String surname, String cellPhoneNumber);

    @Query(value = "select * from user_table where id=:id", nativeQuery = true)
    Optional<User> findById(Long id);
}
