package za.flatrock.assessment.demo.validation.annotation;

import za.flatrock.assessment.demo.validation.CellPhoneNumberValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CellPhoneNumberValidator.class)
public @interface ValidPhoneNumber {
    String message() default "Invalid cell phone number";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
