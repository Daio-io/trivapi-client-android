package io.daio.trivapiclient;

public class TrivapiException extends Exception {

    public TrivapiException(String message) {
        super(message);
    }

    public TrivapiException (String message, Throwable cause) {
        super (message, cause);
    }

}
