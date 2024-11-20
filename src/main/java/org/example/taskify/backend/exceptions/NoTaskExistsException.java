package org.example.taskify.backend.exceptions;

public class NoTaskExistsException extends RuntimeException{

    public NoTaskExistsException(){
        super("No task exists!");
    }

    public NoTaskExistsException(String message){
        super(message);
    }

    public NoTaskExistsException(String message, Throwable throwable){
        super(message, throwable);
    }

    public NoTaskExistsException(Throwable throwable){
        super(throwable);
    }
}
