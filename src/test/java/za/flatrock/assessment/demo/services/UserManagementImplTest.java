package za.flatrock.assessment.demo.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import za.flatrock.assessment.demo.business.logic.impl.UserManagementImpl;
import za.flatrock.assessment.demo.business.validators.RequestValidatorService;
import za.flatrock.assessment.demo.business.validators.impl.RequestValidatorImpl;
import za.flatrock.assessment.demo.exceptions.CellNumberValidationException;
import za.flatrock.assessment.demo.exceptions.UnableToPerformActionException;
import za.flatrock.assessment.demo.models.daos.Role;
import za.flatrock.assessment.demo.models.daos.User;
import za.flatrock.assessment.demo.models.dtos.role.RoleUpdateDTO;
import za.flatrock.assessment.demo.models.dtos.user.CreateUserRequestDTO;
import za.flatrock.assessment.demo.models.dtos.user.UserResponseDTO;
import za.flatrock.assessment.demo.models.enums.RoleEnum;
import za.flatrock.assessment.demo.models.repositories.RoleRepository;
import za.flatrock.assessment.demo.models.repositories.StatusRepository;
import za.flatrock.assessment.demo.models.repositories.UserRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyList;

@ExtendWith(MockitoExtension.class)
public class UserManagementImplTest {

    public RequestValidatorService requestValidatorService = new RequestValidatorImpl();
    private RoleRepository roleRepository = Mockito.mock(RoleRepository.class);
    private UserRepository userRepository = Mockito.mock(UserRepository.class);
    private StatusRepository statusRepository = Mockito.mock(StatusRepository.class);
    public UserManagementImpl userManagementService = new UserManagementImpl(userRepository, roleRepository, statusRepository, requestValidatorService);


    @Test
    void testUserCreation() throws CellNumberValidationException {
        CreateUserRequestDTO createUserRequest = new CreateUserRequestDTO();
        List<String> roleNames = Arrays.asList("USER", "STAFF", "ADMIN");

        createUserRequest.setName("Taku");
        createUserRequest.setSurname("Ndhlovu");
        createUserRequest.setCellNumber("+2799626597");
        createUserRequest.setRoleNames(roleNames);

        User savedUser = new User();
        Role role = new Role();
        role.setName(RoleEnum.USER);
        savedUser.setFirstName("Adams");

        List<Role> retrievedRole = new ArrayList<>();
        retrievedRole.add(role);

        Mockito.when(roleRepository.findByNameIn(Mockito.anyList())).thenReturn(retrievedRole);
        Mockito.when(userRepository.save(Mockito.any())).thenReturn(savedUser);

        UserResponseDTO createdUserResponseDTO = userManagementService.createUser(createUserRequest);
        Mockito.verify(roleRepository, Mockito.times(1)).findByNameIn(anyList());
        Mockito.verify(userRepository, Mockito.times(1)).save(any());

        Assertions.assertEquals(savedUser.getFirstName(), createdUserResponseDTO.getName());
    }

    @Test
    void testFailureUserRoleUpdate() {
        Mockito.when(userRepository.findUserByUniqueUserId(Mockito.anyString())).thenReturn(new User());
        Mockito.when(roleRepository.findRoleByName(Mockito.any(RoleEnum.class))).thenReturn(new Role());
        RoleUpdateDTO roleUpdate = new RoleUpdateDTO();
        roleUpdate.setRole("STAFF");
        roleUpdate.setUniqueId("23834");
        Assertions.assertThrows(UnableToPerformActionException.class, () -> {
            UserResponseDTO userResponseDTO = userManagementService.updateRole(roleUpdate);
        });
    }
}
