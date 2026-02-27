package team2.BUILDWEEK_5.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorsHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequestException.class)
    public ErrorDTO handleBadRequest(BadRequestException ex) {
        return new ErrorDTO(ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(AlreadyExists.class)
    public ErrorDTO handleBadRequest(AlreadyExists ex) {
        return new ErrorDTO(ex.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ErrorDTO handleBadRequest(NotFoundException ex) {
        return new ErrorDTO(ex.getMessage());
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UnauthorizedException.class)
    public ErrorDTO handleBadRequest(UnauthorizedException ex) {
        return new ErrorDTO(ex.getMessage());
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(ValidationException.class)
    public ErrorsDTO handleBadRequest(ValidationException ex) {
        return new ErrorsDTO(ex.getErrorsList());
    }

    @ExceptionHandler(AuthorizationDeniedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorDTO handleAuthorizationDenied(AuthorizationDeniedException ex) {
        return new ErrorDTO(ex.getMessage());
    }
}
