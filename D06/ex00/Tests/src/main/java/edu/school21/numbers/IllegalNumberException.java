package edu.school21.numbers;

public class IllegalNumberException extends RuntimeException {
    public IllegalNumberException() {
        super("ERROR! Number must be > 1");
    }
}
