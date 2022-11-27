package org.katabank.exceptions;

public class BankException extends RuntimeException {
    public BankException(String message) {
        super(message);
    }
}
