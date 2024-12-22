package com.hackathon.inditex.dtos;

import lombok.Getter;

@Getter
public class ResponseDTO {

    String message = "";

    public ResponseDTO(String message) {
        this.message = message;
    }

}
