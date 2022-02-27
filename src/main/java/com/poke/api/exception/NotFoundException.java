package com.poke.api.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException() {
        super("Pokemon not found!!");
    }
}