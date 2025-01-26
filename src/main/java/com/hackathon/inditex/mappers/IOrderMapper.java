package com.hackathon.inditex.mappers;

import com.hackathon.inditex.entities.Order;
import com.hackathon.inditex.dtos.OrderDTO;

public interface IOrderMapper {
    Order orderDTOtoOrder(OrderDTO newOrderDTO);
}
