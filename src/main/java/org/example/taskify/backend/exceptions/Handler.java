package org.example.taskify.backend.exceptions;

import jakarta.mail.MessagingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.io.UnsupportedEncodingException;

@ControllerAdvice
public class Handler {

    @ExceptionHandler(NoTaskExistsException.class)
    public String handleNoTaskExistsException(NoTaskExistsException ex){
        return ex.getMessage();
    }

    @ExceptionHandler(org.example.taskManager.BackEnd.exceptions.DuplicateTitleException.class)
    public String handleDuplicateTitleException(org.example.taskManager.BackEnd.exceptions.DuplicateTitleException ex){
        return ex.getMessage();
    }

    @ExceptionHandler(MessagingException.class)
    public String handleMassagingException(MessagingException ex){
        return ex.getMessage();
    }

    @ExceptionHandler(UnsupportedEncodingException.class)
    public String handleUnsupportedEncodingException(UnsupportedEncodingException ex){
        return ex.getMessage();
    }
}
