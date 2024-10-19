package com.example.recipe.core.utils.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class BusinessProblemDetails extends ProblemDetails {
    public BusinessProblemDetails(String detail) {
        setDetail(detail);
        setType("Business Exception");
        setStatus(HttpStatus.BAD_REQUEST.value());
        setTitle("Business Rule Violation");
    }
}
