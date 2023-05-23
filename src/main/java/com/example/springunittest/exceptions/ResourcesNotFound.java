package com.example.springunittest.exceptions;

public class ResourcesNotFound extends RuntimeException {

    public ResourcesNotFound(String message) {
        super(message);
    }
}
