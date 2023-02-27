package za.flatrock.assessment.demo.validation;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import za.flatrock.assessment.demo.entities.User;

import java.util.Optional;

@Slf4j
@NoArgsConstructor
public class CreateUserValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return CreateUserValidator.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Optional<User> user = (Optional<User>) target;
        if (user.isPresent()) {
            log.error("User already exist {}", user);
            errors.reject("User already exist");
        }
    }
}
