package za.flatrock.assessment.demo.exceptions;

public class CellNumberLengthInvalidException extends Exception {
    public CellNumberLengthInvalidException(String errorMessage) {
        super(errorMessage);
    }
}
