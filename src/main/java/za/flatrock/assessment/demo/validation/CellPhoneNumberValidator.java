package za.flatrock.assessment.demo.validation;

import za.flatrock.assessment.demo.util.CellPhoneNumberUtil;
import za.flatrock.assessment.demo.validation.annotation.ValidPhoneNumber;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CellPhoneNumberValidator implements ConstraintValidator<ValidPhoneNumber, String> {
    @Override
    public void initialize(ValidPhoneNumber constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        String formattedCellPhoneNumber = CellPhoneNumberUtil.format(value);

        Pattern pattern = Pattern.compile("^[+0-9]{12}$");
        Matcher matcher = pattern.matcher(formattedCellPhoneNumber);

        if (!matcher.matches())
            return false;

        return true;
    }
}
