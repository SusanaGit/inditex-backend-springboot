package com.hackathon.inditex.dtos;

import com.hackathon.inditex.entities.Coordinates;

public class OrderDTO {

    private Long customerId;

    private String size;

    private Coordinates coordinates;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }
}
