package za.flatrock.assessment.demo.validation;

import org.springframework.util.StringUtils;
import za.flatrock.assessment.demo.models.enums.RoleEnum;
import za.flatrock.assessment.demo.validation.annotation.ValidRole;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class RoleValidator implements ConstraintValidator<ValidRole, String> {
    @Override
    public void initialize(ValidRole constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {

        if(!StringUtils.hasText(value))
            return false;

        if (!RoleEnum.isValidRole(value))
            return false;

        return true;
    }
}
