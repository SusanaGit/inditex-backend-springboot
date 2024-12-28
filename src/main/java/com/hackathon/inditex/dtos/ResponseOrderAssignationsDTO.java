package com.hackathon.inditex.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ResponseOrderAssignationsDTO {

    @JsonProperty("processed-orders")
    private List<ProcessedOrderDTO> listProcessedOrderDTO;

}