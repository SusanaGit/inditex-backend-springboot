package com.hackathon.inditex.dtos;

import com.hackathon.inditex.entities.Coordinates;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ResponseOrderDTO {

    private Long orderId;

    private Long customerId;

    private String size;

    private String assignedLogisticsCenter;

    private Coordinates coordinates;

    private String status;

    private String message;
}
