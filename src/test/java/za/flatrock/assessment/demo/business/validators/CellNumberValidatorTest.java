package za.flatrock.assessment.demo.business.validators;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import za.flatrock.assessment.demo.exceptions.CellNumberValidationException;
import za.flatrock.assessment.demo.models.dtos.user.CreateUserRequestDTO;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@DirtiesContext
public class CellNumberValidatorTest {

    @Autowired
    private RequestValidatorService cellNumberValidatorService;

    @Test
    public void testThatCellNumberIsValid() throws CellNumberValidationException {
        String cellNumber = "+27799626597";
        Assertions.assertTrue(cellNumberValidatorService.validateRequest(createTestUserRequest(cellNumber)));
    }

    @Test
    public void testThatWhenLength_isGreaterThan12_thenAnExceptionIsThrown() {
        String cellNumber = "+277996265973";
        CellNumberValidationException exception = Assertions.assertThrows(CellNumberValidationException.class, () -> {
            Assertions.assertTrue(cellNumberValidatorService.validateRequest(createTestUserRequest(cellNumber)));
        });
        Assertions.assertEquals("Cell number exceeds the allowed maximum length.", exception.getMessage());
    }

    @Test
    public void testThatWhenWhiteSpace_isPresent_thenAnExceptionIsThrown() {
        String cellNumber = "+2779962697 ";
        CellNumberValidationException exception = Assertions.assertThrows(CellNumberValidationException.class, () -> {
            Assertions.assertTrue(cellNumberValidatorService.validateRequest(createTestUserRequest(cellNumber)));
        });
        Assertions.assertEquals("Cell number contains whitespace.", exception.getMessage());
    }

    @Test
    public void testThatWhenAlphaNumericCharacter_isPresent_thenAnExceptionIsThrown() {
        String cellNumber = "+2779926597e";
        CellNumberValidationException exception = Assertions.assertThrows(CellNumberValidationException.class, () -> {
            Assertions.assertTrue(cellNumberValidatorService.validateRequest(createTestUserRequest(cellNumber)));
        });
        Assertions.assertEquals("Cell number contains non alphanumeric characters.", exception.getMessage());
    }

    @Test
    public void testThatWhenDialingCode_isNotSouthAfrica_thenAnExceptionIsThrown() {
        String cellNumber = "+28799626597";
        CellNumberValidationException exception = Assertions.assertThrows(CellNumberValidationException.class, () -> {
            Assertions.assertTrue(cellNumberValidatorService.validateRequest(createTestUserRequest(cellNumber)));
        });
        Assertions.assertEquals("Cell number country code invalid.", exception.getMessage());
    }

    private CreateUserRequestDTO createTestUserRequest(String cellNumber) {
        List<String> roles = new ArrayList<>();
        roles.add("USER");
        return new CreateUserRequestDTO("FirstName", "Surname", cellNumber, roles);
    }

}
