package com.hackathon.inditex.exceptions;

public class CurrentLoadMoreThanMaxCapacityException extends RuntimeException {

    public CurrentLoadMoreThanMaxCapacityException(String message) {
        super(message);
    }

}
