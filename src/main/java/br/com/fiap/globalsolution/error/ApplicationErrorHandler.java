package br.com.fiap.globalsolution.error;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.fiap.globalsolution.error.exceptions.ResourceNotFoundException;
import br.com.fiap.globalsolution.validation.RestValidationError;

@RestControllerAdvice
public class ApplicationErrorHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<RestValidationError> validationHandler(MethodArgumentNotValidException e){
        return e.getBindingResult()
                    .getFieldErrors()
                    .stream()
                    .map(err -> new RestValidationError(err.getField(), err.getDefaultMessage()))
                    .collect(Collectors.toList());
                
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public RestValidationError validationHandler(DataIntegrityViolationException e){
        return new RestValidationError("Violação de integridade", e.getMessage());
                
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public RestValidationError validationHandler(ResourceNotFoundException e){
        return new RestValidationError(null, e.getMessage());
                
    }
    
}
