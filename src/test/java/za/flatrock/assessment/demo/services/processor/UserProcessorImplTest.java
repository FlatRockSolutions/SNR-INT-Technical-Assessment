package za.flatrock.assessment.demo.services.processor;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import za.flatrock.assessment.demo.business.logic.UserManagementService;
import za.flatrock.assessment.demo.exceptions.CellNumberValidationException;
import za.flatrock.assessment.demo.exceptions.UserNotFoundException;
import za.flatrock.assessment.demo.models.dtos.UserAPIResponse;
import za.flatrock.assessment.demo.models.dtos.user.CreateUserRequestDTO;
import za.flatrock.assessment.demo.models.dtos.user.DeleteUserResponseDTO;
import za.flatrock.assessment.demo.models.dtos.user.UserResponseDTO;
import za.flatrock.assessment.demo.models.dtos.user.UserSearchDTO;
import za.flatrock.assessment.demo.services.users.processor.UserProcessorImpl;
import za.flatrock.assessment.demo.services.users.processor.UserProcessorService;

@ExtendWith(SpringExtension.class)
public class UserProcessorImplTest {
    private UserManagementService userManagementService = Mockito.mock(UserManagementService.class);
    public UserProcessorService userProcessor = new UserProcessorImpl(userManagementService);

    @Test
    public void testSuccessAPIResponseForCreate() throws CellNumberValidationException {
        CreateUserRequestDTO testRequest = new CreateUserRequestDTO();
        UserResponseDTO response = new UserResponseDTO("FirstName", "Surname", "+27622145294");
        Mockito.when(userManagementService.createUser(testRequest)).thenReturn(response);
        UserAPIResponse apiResponse = userProcessor.createUser(testRequest);
        Assertions.assertEquals(HttpStatus.CREATED.value(), apiResponse.getResponseCode());
        Assertions.assertEquals("Account created successfully", apiResponse.getMessage());
        Assertions.assertEquals(true, apiResponse.isModified());
    }

    @Test
    public void testFailureAPIResponseForCreate() throws CellNumberValidationException {
        CreateUserRequestDTO testRequest = new CreateUserRequestDTO();
        UserResponseDTO response = new UserResponseDTO("FirstName", "Surname", "+27622145294");
        Mockito.when(userManagementService.createUser(testRequest)).thenThrow(CellNumberValidationException.class);
        UserAPIResponse apiResponse = userProcessor.createUser(testRequest);
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), apiResponse.getResponseCode());
        Assertions.assertNotEquals("Account created successfully", apiResponse.getMessage());
        Assertions.assertEquals(false, apiResponse.isModified());
    }

    @Test
    public void testSuccessAPIResponseForDelete() {
        String id = "1234-4567-8901";
        UserResponseDTO response = new UserResponseDTO("FirstName", "Surname", "+27622145294");
        Mockito.when(userManagementService.deleteUserById(id)).thenReturn(new DeleteUserResponseDTO());
        UserAPIResponse apiResponse = userProcessor.deleteUserById(id);
        Assertions.assertEquals(HttpStatus.OK.value(), apiResponse.getResponseCode());
        Assertions.assertEquals(true, apiResponse.isModified());
    }

    @Test
    public void testFailureAPIResponseForDelete() throws CellNumberValidationException {
        String id = "1234-4567-8901";
        UserResponseDTO response = new UserResponseDTO("FirstName", "Surname", "+27622145294");
        Mockito.when(userManagementService.deleteUserById(id)).thenThrow(RuntimeException.class);
        UserAPIResponse apiResponse = userProcessor.deleteUserById(id);
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), apiResponse.getResponseCode());
        Assertions.assertEquals(false, apiResponse.isModified());
    }


    @Test
    public void testSuccessAPIResponseForFind() throws UserNotFoundException {
        UserSearchDTO testSearchDTO = new UserSearchDTO();
        testSearchDTO.setFirstName("FirstName");
        testSearchDTO.setLastName("LastName");
        testSearchDTO.setCellNumber("+27835612396");
        Mockito.when(userManagementService.findUserByNameSurnameAndCellNumber(Mockito.any(UserSearchDTO.class))).
                thenReturn(new UserResponseDTO());
        UserAPIResponse apiResponse = userProcessor.findUser(testSearchDTO);
        Assertions.assertEquals(HttpStatus.FOUND.value(), apiResponse.getResponseCode());
    }


    @Test
    public void testFailureAPIResponseForFind() throws UserNotFoundException {
        UserSearchDTO testSearchDTO = new UserSearchDTO();
        testSearchDTO.setFirstName("FirstName");
        testSearchDTO.setLastName("LastName");
        testSearchDTO.setCellNumber("+27835612396");
        Mockito.when(userManagementService.findUserByNameSurnameAndCellNumber(Mockito.any(UserSearchDTO.class))).
                thenThrow(UserNotFoundException.class);
        UserAPIResponse apiResponse = userProcessor.findUser(testSearchDTO);
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), apiResponse.getResponseCode());
    }


}
