package za.flatrock.assessment.demo.exceptions;

import lombok.Data;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
public class BaseException extends RuntimeException {

    private final HttpStatus status;
    private final List<String> reasons;

    public BaseException(HttpStatus status, String reasons) {
        this.status = status;
        this.reasons = List.of(reasons);
    }

    public BaseException(HttpStatus status, List<ObjectError> allErrors) {
        this.status = status;
        List<String> reasons = Arrays.stream(Objects.requireNonNull(allErrors.get(0).getCodes()))
                .skip(1)
                .collect(Collectors.toList());
        this.reasons = reasons;
    }
}
