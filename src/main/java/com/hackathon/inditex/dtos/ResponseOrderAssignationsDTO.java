package com.hackathon.inditex.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ResponseOrderAssignationsDTO {

    private List<ProcessedOrderDTO> listProcessedOrders;

}