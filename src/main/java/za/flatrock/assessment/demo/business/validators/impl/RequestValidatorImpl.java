package za.flatrock.assessment.demo.business.validators.impl;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import za.flatrock.assessment.demo.business.validators.RequestValidatorService;
import za.flatrock.assessment.demo.exceptions.*;
import za.flatrock.assessment.demo.models.dtos.user.CreateUserRequestDTO;
import za.flatrock.assessment.demo.models.enums.DialingCodeEnum;

import java.util.List;

@Service
public class RequestValidatorImpl implements RequestValidatorService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public boolean validateRequest(CreateUserRequestDTO request) throws CellNumberValidationException {
        String cellNumber = request.getCellNumber();
        String dialingCode = cellNumber.substring(0, 3);
        try {
            if (isValidLength(cellNumber) && isValidRSADiallingCode(dialingCode) && isValidLength(cellNumber)
                    && hasNoWhiteSpace(cellNumber) && isStrictlyAlphaNumeric(cellNumber)) {
                return true;
            }
            return false;
        } catch (CountryCodeInvalidException | CellNumberLengthInvalidException | ContainsWhiteSpaceException
                 | ContrainsNonAlphaNumericException e) {
            throw new CellNumberValidationException(e.getMessage());
        }
    }

    private boolean isValidRSADiallingCode(String code) throws CountryCodeInvalidException {
        if (code.equalsIgnoreCase(DialingCodeEnum.SOUTH_AFRICA.getCode()))
            return true;
        throw new CountryCodeInvalidException("Cell number country code invalid.");
    }

    private boolean isValidLength(String cellNumber) throws CellNumberLengthInvalidException {
        int length = cellNumber.length();
        if (length <= 12)
            return true;
        throw new CellNumberLengthInvalidException("Cell number exceeds the allowed maximum length.");
    }

    private boolean hasNoWhiteSpace(String cellNumber) throws ContainsWhiteSpaceException {
        if (StringUtils.containsWhitespace(cellNumber))
            throw new ContainsWhiteSpaceException("Cell number contains whitespace.");
        return true;
    }

    private boolean isStrictlyAlphaNumeric(String cellNumber) throws ContrainsNonAlphaNumericException {
        String trimmedCellNumber = cellNumber.substring(1);
        logger.info(trimmedCellNumber);
        String regex = "[0-9]+";
        if (trimmedCellNumber.matches(regex))
            return true;
        throw new ContrainsNonAlphaNumericException("Cell number contains non alphanumeric characters.");
    }

    private boolean isValidNames(CreateUserRequestDTO request) {
        String firstName = request.getName();
        String lastName = request.getSurname();
        List<String> rolls = request.getRoleNames();
        return firstName != null && lastName != null && rolls != null;
    }
}
