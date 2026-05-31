package tech.galactictournament.config.exceptions;

import jakarta.persistence.EntityExistsException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse fieldErrors(HttpServletRequest request, MethodArgumentNotValidException ex) {

        // Errores
        List<ObjectError> errors = ex.getAllErrors();

        // Detalles
        List<ErrorResponse.ErrorDetails> errorDetails = new ArrayList<>();

        for ( ObjectError error : errors) {
            ErrorResponse.ErrorDetails detail = new ErrorResponse.ErrorDetails();
            detail.setFieldName(((FieldError) error).getField());
            detail.setMessage(error.getDefaultMessage());
            errorDetails.add(detail);
        }

        ErrorResponse response = new ErrorResponse(ex, request.getRequestURI(), errorDetails);

        return response;
    }

    @ExceptionHandler({BindException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse fieldErrors(HttpServletRequest request, BindException ex) {

        // Errores
        List<FieldError> errors = ex.getBindingResult().getFieldErrors();

        // Detalles
        List<ErrorResponse.ErrorDetails> errorDetails = new ArrayList<>();

        for ( FieldError error : errors) {
            ErrorResponse.ErrorDetails detail = new ErrorResponse.ErrorDetails();
            detail.setFieldName(error.getField());
            detail.setMessage(error.getDefaultMessage());
            errorDetails.add(detail);
        }

        ErrorResponse response = new ErrorResponse(ex, request.getRequestURI(), errorDetails);

        return response;
    }

    @ExceptionHandler({ NotFoundException.class })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorMessage notFoundRequest(HttpServletRequest request, Exception exception) {
        return new ErrorMessage(exception, request.getRequestURI());
    }

    @ExceptionHandler({
            BadRequestException.class,
            ServletException.class,
            org.springframework.dao.DuplicateKeyException.class,
            org.springframework.web.HttpRequestMethodNotSupportedException.class,
            org.springframework.web.bind.MissingRequestHeaderException.class,
            org.springframework.web.bind.MissingServletRequestParameterException.class,
            org.springframework.web.method.annotation.MethodArgumentTypeMismatchException.class,
            org.springframework.http.converter.HttpMessageNotReadableException.class,
            MissingServletRequestPartException.class,
            EntityExistsException.class
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorMessage badRequest(HttpServletRequest request, Exception exception) {
        return new ErrorMessage(exception, request.getRequestURI());
    }
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler({
            HttpClientErrorException.Unauthorized.class,
    })
    public void unauthorized() {
        // Nada que hacer, no se permiten mensajes en el cuerpo
    }

    @ExceptionHandler({ Exception.class })
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorMessage fatalErrorUnexpectedException(HttpServletRequest request, Exception exception) {
        return new ErrorMessage(exception, request.getRequestURI());
    }

}
