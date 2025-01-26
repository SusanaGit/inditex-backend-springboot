package com.hackathon.inditex.dtos;

public class ProcessedOrderDTO {

    private Double distance;

    private long orderId;

    private String assignedLogisticsCenter;

    private String status;

    private String message;

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public String getAssignedLogisticsCenter() {
        return assignedLogisticsCenter;
    }

    public void setAssignedLogisticsCenter(String assignedLogisticsCenter) {
        this.assignedLogisticsCenter = assignedLogisticsCenter;
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