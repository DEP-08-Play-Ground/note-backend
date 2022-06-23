package ik.ijse.dep8.note.advice;

import ik.ijse.dep8.note.service.exception.DuplicateEmailException;
import ik.ijse.dep8.note.service.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicateEmailException.class)
    public void handleDuplicateEmailException(DuplicateEmailException e){
        e.initCause(new ResponseStatusException(HttpStatus.CONFLICT,e.getMessage(),e));
    }

    @ExceptionHandler(NotFoundException.class)
    public void handleNotFoundException(NotFoundException e){
        e.initCause(new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage(),e));
    }


}
