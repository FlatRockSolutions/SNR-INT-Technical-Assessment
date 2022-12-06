package za.flatrock.assessment.demo.exceptions;

public class ContrainsNonAlphaNumericException extends Exception {
    public ContrainsNonAlphaNumericException(String errorMessage) {
        super(errorMessage);
    }
}
