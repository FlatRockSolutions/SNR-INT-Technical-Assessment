package za.flatrock.assessment.demo.business.validators;

import za.flatrock.assessment.demo.exceptions.CellNumberValidationException;
import za.flatrock.assessment.demo.models.dtos.user.CreateUserRequestDTO;

public interface RequestValidatorService {
    boolean validateRequest(CreateUserRequestDTO request) throws CellNumberValidationException;
}
