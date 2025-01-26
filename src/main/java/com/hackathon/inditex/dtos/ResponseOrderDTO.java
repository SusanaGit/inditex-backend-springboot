package com.hackathon.inditex.dtos;

import com.hackathon.inditex.entities.Coordinates;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ResponseOrderDTO {

    private Long orderId;

    private Long customerId;

    private String size;

    private String assignedLogisticsCenter;

    private Coordinates coordinates;

    private String status;

    private String message;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

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

    public String getAssignedLogisticsCenter() {
        return assignedLogisticsCenter;
    }

    public void setAssignedLogisticsCenter(String assignedLogisticsCenter) {
        this.assignedLogisticsCenter = assignedLogisticsCenter;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
