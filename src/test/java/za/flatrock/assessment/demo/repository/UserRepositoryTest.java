package za.flatrock.assessment.demo.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import za.flatrock.assessment.demo.entities.Role;
import za.flatrock.assessment.demo.entities.User;
import za.flatrock.assessment.demo.repositories.RoleRepository;
import za.flatrock.assessment.demo.repositories.UserRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Test
    void saveUser() {
        Role role = roleRepository.save(new Role("ADMIN"));
        User user = userRepository.save(new User("bob", "builder", "+27726684422", role));

        assertNotNull(role);
        assertNotNull(user);
    }
    @Test
    void findByUserId() {
        Role role = roleRepository.save(new Role("ADMIN"));
        userRepository.save(new User("bob", "builder", "+27726684422", role));

        Optional<User> user = userRepository.findByUserId("bob".toUpperCase(), "builder".toUpperCase(),"+27726684422");

        assertTrue(user.isPresent());
    }

    @Test
    void findById() {
        Role role = roleRepository.save(new Role("ADMIN"));
        User newUser = userRepository.save(new User("bob", "builder", "+27726684422", role));

        Optional<User> user = userRepository.findById(newUser.getId());

        assertTrue(user.isPresent());
    }
}
