package com.example.recipe.core.utils.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ValidationProblemDetails extends ProblemDetails{
    private List<String> errors;

    public ValidationProblemDetails(List<String> errors) {
        setTitle("Validation Rule Violation");
        setDetail("One or more Validation Error(s)!");;
        setType("ValidationException");
        setStatus(HttpStatus.BAD_REQUEST.value());
        this.errors = errors;
    }
}