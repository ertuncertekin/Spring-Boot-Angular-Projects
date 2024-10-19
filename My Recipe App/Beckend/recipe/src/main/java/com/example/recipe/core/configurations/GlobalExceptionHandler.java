package com.example.recipe.core.configurations;

import com.example.recipe.core.utils.exceptions.BusinessException;
import com.example.recipe.core.utils.exceptions.BusinessProblemDetails;
import com.example.recipe.core.utils.exceptions.ProblemDetails;
import com.example.recipe.core.utils.exceptions.ValidationProblemDetails;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({BusinessException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BusinessProblemDetails handleRunTimeException(BusinessException exception){
        BusinessProblemDetails problemDetails=new BusinessProblemDetails(exception.getMessage());
        return problemDetails;
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationProblemDetails handleValidationException(MethodArgumentNotValidException exception) {
        List<String> errorMessages=new ArrayList<>();
        List<FieldError> errors=exception.getBindingResult().getFieldErrors();
        for(FieldError error:errors)
        {
            errorMessages.add(error.getDefaultMessage());
        }
        ValidationProblemDetails validationProblemDetails=new ValidationProblemDetails(errorMessages);
        return validationProblemDetails;
    }
}
