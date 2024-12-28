package com.hackathon.inditex.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProcessedOrderDTO {

    private Double distance;

    private long orderId;

    private String assignedLogisticsCenter;

    private String status;

}