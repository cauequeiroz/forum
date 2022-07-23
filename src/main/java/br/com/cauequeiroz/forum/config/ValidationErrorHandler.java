package br.com.cauequeiroz.forum.config;

import br.com.cauequeiroz.forum.resource.response.ErrorFieldResponse;
import br.com.cauequeiroz.forum.resource.response.ErrorMessageResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestControllerAdvice
public class ValidationErrorHandler {

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ErrorFieldResponse> handleMethodArgumentNotValid(MethodArgumentNotValidException exception) {

        return exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> new ErrorFieldResponse(error.getField(), error.getDefaultMessage()))
                .toList();
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ErrorMessageResponse handleHttpMessageNotReadable(HttpMessageNotReadableException exception) {

        return new ErrorMessageResponse("Invalid JSON format.");
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(EntityNotFoundException.class)
    public ErrorMessageResponse handleEntityNotFound() {

        return new ErrorMessageResponse("This ID do not exist in our database.");
    }
}
