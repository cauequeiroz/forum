package br.com.cauequeiroz.forum.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ValidationErrorHandler {

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ErrorDTO> handleMethodArgumentNotValid(MethodArgumentNotValidException exception) {

        return exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> new ErrorDTO(error.getField(), error.getDefaultMessage()))
                .toList();
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ErrorDTO handleHttpMessageNotReadable(HttpMessageNotReadableException exception) {

        return new ErrorDTO("Invalid JSON message.", exception.getMessage());
    }
}
