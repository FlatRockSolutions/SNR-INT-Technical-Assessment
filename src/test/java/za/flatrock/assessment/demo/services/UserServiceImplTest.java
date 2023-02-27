package za.flatrock.assessment.demo.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;
import za.flatrock.assessment.demo.entities.Role;
import za.flatrock.assessment.demo.entities.User;
import za.flatrock.assessment.demo.exceptions.BaseException;
import za.flatrock.assessment.demo.models.SearchCriteria;
import za.flatrock.assessment.demo.models.enums.RoleEnum;
import za.flatrock.assessment.demo.models.requests.CreateUserRequest;
import za.flatrock.assessment.demo.models.responses.api.ApiSearchCriteriaResponse;
import za.flatrock.assessment.demo.repositories.RoleRepository;
import za.flatrock.assessment.demo.repositories.UserRepository;
import za.flatrock.assessment.demo.util.CellPhoneNumberUtil;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    Validator validator;

    @Mock
    Errors errors;

    @Mock
    UserRepository userRepository;

    @Mock
    RoleRepository roleRepository;

    @InjectMocks
    private UserServiceImpl userService;

    private Role role = new Role(1L,RoleEnum.ADMIN);

    private final User user = new User("TestName", "TestSurname", "+27726684422", role);

    private final CreateUserRequest request = new CreateUserRequest("TestName","TestSurname", CellPhoneNumberUtil.format("(+27)72-668-4422"),"admin");

    private final SearchCriteria searchCriteria = new SearchCriteria("TestName", "TestSurname", "+27726684422");

    @Test
    void createUser() {
        Mockito
                .when(roleRepository.save(new Role("ADMIN")))
                .thenReturn(role).getMock();

        Mockito
                .when(userRepository.save(new User("TestName", "TestSurname", "+27726684422", role)))
                .thenReturn(user);

        userService.create(request, errors);

        Mockito.verify(userRepository, atLeast(1)).save(any());
        Mockito.verify(roleRepository, atLeast(1)).save(any());
    }

    @Test
    void duplicateUserCheck() {
        Optional<User> user1 = Optional.of(user);
        Mockito
                .when(userRepository.findByUserId(
                        "TestName".toUpperCase(),
                        "TestSurname".toUpperCase(),
                        "+27726684422"))
                .thenReturn(user1);

        Mockito
                .when(errors.hasErrors())
                .thenReturn(true);

        String userAlreadyExist = "User already exist";

        Mockito
                .when(errors.getAllErrors())
                .thenReturn(List.of(new ObjectError("", getErrorCodes(userAlreadyExist), null, userAlreadyExist)));

        assertThrows(BaseException.class,
                () -> userService.create(request, errors),
                "Different exception thrown!");
    }

    @Test
    void deleteUser() {
        Mockito.when(userRepository.findById(2L)).thenReturn(Optional.of(user));
        userService.deleteUser(2L);
        Mockito.verify(userRepository,times(1)).delete(user);
    }
    @Test
    void deleteNonExistingUser() {
        long id = 2L;
        Mockito.when(userRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(BaseException.class,
                () -> userService.deleteUser(id),
                "Different exception thrown!");
    }

    @Test
    void searchUser() {
        Mockito.when(userRepository
                        .findByUserId(searchCriteria.getName(), searchCriteria.getSurname(), searchCriteria.getCellPhoneNumber()))
                .thenReturn(Optional.of(user));

        ApiSearchCriteriaResponse search = (ApiSearchCriteriaResponse) userService.search(searchCriteria);

        assertNotNull(search.getData());

    }

    @Test
    void searchUserNotFound() {
        Mockito.when(userRepository
                        .findByUserId(searchCriteria.getName(), searchCriteria.getSurname(), searchCriteria.getCellPhoneNumber()))
                .thenReturn(Optional.empty());

        ApiSearchCriteriaResponse search = (ApiSearchCriteriaResponse) userService.search(searchCriteria);

        assertEquals(search.getData().size(),0);
    }

    private String[] getErrorCodes(String errorMessage) {
        String [] codes = new String [2];
        codes[0] = this.getClass()+errorMessage;
        codes[1] = errorMessage;
        return codes;
    }
}
