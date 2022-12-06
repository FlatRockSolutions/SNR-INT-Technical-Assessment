package za.flatrock.assessment.demo.exceptions;

public class UnableToPerformActionException extends Exception {
    public UnableToPerformActionException(String errorMessage) {
        super(errorMessage);
    }
}
