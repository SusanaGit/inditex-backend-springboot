package com.hackathon.inditex.dtos;

import com.hackathon.inditex.entities.Coordinates;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDTO {

    private Long customerId;

    private String size;

    private Coordinates coordinates;

}
