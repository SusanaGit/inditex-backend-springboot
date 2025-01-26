package com.hackathon.inditex.dtos;

import com.hackathon.inditex.entities.Coordinates;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CenterDTO {

    private String name;

    private String capacity;

    private String status;

    private Integer maxCapacity;

    private Integer currentLoad;

    private Coordinates coordinates;

}
