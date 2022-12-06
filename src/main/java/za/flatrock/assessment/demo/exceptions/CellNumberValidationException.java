package za.flatrock.assessment.demo.exceptions;

public class CellNumberValidationException extends Exception {
    public CellNumberValidationException(String errorMessage) {
        super(errorMessage);
    }
}
