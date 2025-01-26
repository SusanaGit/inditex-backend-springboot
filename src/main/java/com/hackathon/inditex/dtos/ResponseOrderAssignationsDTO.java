package com.hackathon.inditex.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class ResponseOrderAssignationsDTO {

    @JsonProperty("processed-orders")
    private List<ProcessedOrderDTO> listProcessedOrderDTO;

    public List<ProcessedOrderDTO> getListProcessedOrderDTO() {
        return listProcessedOrderDTO;
    }

    public void setListProcessedOrderDTO(List<ProcessedOrderDTO> listProcessedOrderDTO) {
        this.listProcessedOrderDTO = listProcessedOrderDTO;
    }
}