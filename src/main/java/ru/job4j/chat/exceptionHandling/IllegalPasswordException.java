package ru.job4j.chat.exceptionHandling;

public class IllegalPasswordException extends RuntimeException {

    public IllegalPasswordException(String message) {
        super(message);
    }
}
