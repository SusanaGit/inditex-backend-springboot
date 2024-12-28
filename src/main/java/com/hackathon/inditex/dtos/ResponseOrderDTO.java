package com.hackathon.inditex.dtos;

import com.hackathon.inditex.Entities.Order;
import lombok.Getter;

@Getter
public class ResponseOrderDTO {

    Order order;
    String messageOrder;

    public ResponseOrderDTO(Order order, String messageOrder) {
        this.order = order;
        this.messageOrder = messageOrder;
    }
}
