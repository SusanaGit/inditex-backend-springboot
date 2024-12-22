package com.hackathon.inditex.exceptions;

import com.hackathon.inditex.dtos.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CoordinatesExistException.class)
    public ResponseEntity<ResponseDTO> handleCoordinatesExistException(CoordinatesExistException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDTO(e.getMessage()));
    }

    @ExceptionHandler(CurrentLoadMoreThanMaxCapacityException.class)
    public ResponseEntity<ResponseDTO> handleCurrentLoadMoreThanMaxCapacityException(CurrentLoadMoreThanMaxCapacityException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDTO(e.getMessage()));
    }

}
