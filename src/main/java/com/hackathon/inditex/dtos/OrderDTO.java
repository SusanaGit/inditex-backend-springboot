package com.hackathon.inditex.dtos;

import com.hackathon.inditex.entities.Coordinates;
import lombok.Getter;

@Getter
public class OrderDTO {

    private Long customerId;

    private String size;

    private Coordinates coordinates;

}
