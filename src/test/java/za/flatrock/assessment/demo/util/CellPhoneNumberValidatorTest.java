package za.flatrock.assessment.demo.util;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import za.flatrock.assessment.demo.validation.CellPhoneNumberValidator;

import javax.validation.ConstraintValidatorContext;

@ExtendWith(MockitoExtension.class)
public class CellPhoneNumberValidatorTest {
    @Mock
    ConstraintValidatorContext constraintValidatorContext;


    @Test
    public void validCellPhoneNumber(){
        CellPhoneNumberValidator cellPhoneNumberValidator = new CellPhoneNumberValidator();

        boolean isValid = cellPhoneNumberValidator.isValid("(+27)72-668-4422",constraintValidatorContext);
        Assert.assertTrue(isValid);

        isValid = cellPhoneNumberValidator.isValid("+27726684422",constraintValidatorContext);
        Assert.assertTrue(isValid);
    }

    @Test
    public void inValidCellPhoneNumber(){
        CellPhoneNumberValidator cellPhoneNumberValidator = new CellPhoneNumberValidator();

        boolean isValid = cellPhoneNumberValidator.isValid("(+27)7s2-668-44d22",constraintValidatorContext);
        Assert.assertFalse(isValid);

        isValid = cellPhoneNumberValidator.isValid("4372-668-442233",constraintValidatorContext);
        Assert.assertFalse(isValid);

        isValid = cellPhoneNumberValidator.isValid("27766843",constraintValidatorContext);
        Assert.assertFalse(isValid);

        isValid = cellPhoneNumberValidator.isValid("asdasdad",constraintValidatorContext);
        Assert.assertFalse(isValid);
    }

}
