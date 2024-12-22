package com.hackathon.inditex.dtos;

import com.hackathon.inditex.Entities.Coordinates;
import lombok.Getter;

@Getter
public class CenterDTO {

    private String name;

    private String capacity;

    private String status;

    private int maxCapacity;

    private int currentLoad;

    private Coordinates coordinates;

}
