package com.hackathon.inditex.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ResponseOrderAssignationsDTO {

    @JsonProperty("processed-orders")
    private List<ProcessedOrderDTO> listProcessedOrderDTO;

}