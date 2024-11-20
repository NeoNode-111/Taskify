package org.example.taskManager.BackEnd.exceptions;

public class EmailSendingException extends RuntimeException{

    public EmailSendingException() {
        super("Something went wrong while sending the email!");
    }

    public EmailSendingException(String message){
        super(message);
    }

    public EmailSendingException(String message, Throwable cause){
        super(message, cause);
    }

    public EmailSendingException(Throwable cause){
        super(cause);
    }
}
