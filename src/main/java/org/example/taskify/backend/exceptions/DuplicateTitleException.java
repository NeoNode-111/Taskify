package org.example.taskManager.BackEnd.exceptions;

public class DuplicateTitleException extends RuntimeException{

    public DuplicateTitleException() {
        super("Duplicate Title!");
    }

    public DuplicateTitleException(String message) {
        super(message);
    }

    public DuplicateTitleException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateTitleException(Throwable cause) {
        super(cause);
    }
}
