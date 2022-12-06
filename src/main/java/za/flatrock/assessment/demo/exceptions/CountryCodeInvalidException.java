package za.flatrock.assessment.demo.exceptions;

public class CountryCodeInvalidException extends Exception {

    public CountryCodeInvalidException(String errorMessage) {
        super(errorMessage);
    }
}
