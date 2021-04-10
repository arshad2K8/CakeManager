package com.cake.manager.exceptions;

import com.cake.manager.domain.Cake;

public class DuplicateCakeException extends RuntimeException {

    private Cake cake;

    public DuplicateCakeException(String message, Cake cake) {
        super(message);
        this.cake = cake;
    }

    public Cake getCake() {
        return cake;
    }
}
