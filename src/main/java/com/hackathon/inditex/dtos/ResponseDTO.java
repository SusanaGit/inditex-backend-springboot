package com.hackathon.inditex.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseDTO {

    String message = "";

    public ResponseDTO(String message) {
        this.message = message;
    }

}
