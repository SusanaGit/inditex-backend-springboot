package com.hackathon.inditex.mappers;

import com.hackathon.inditex.Entities.Coordinates;
import com.hackathon.inditex.Entities.Order;
import com.hackathon.inditex.dtos.OrderDTO;
import org.springframework.stereotype.Service;

@Service
public class OrderMapper implements IOrderMapper {

    @Override
    public Order orderDTOtoOrder(OrderDTO newOrderDTO) {

        Order newOrder = new Order();

        newOrder.setCustomerId(newOrderDTO.getCustomerId());
        newOrder.setSize(newOrderDTO.getSize());

        newOrder.setStatus("PENDING");
        newOrder.setAssignedCenter(null);

        Coordinates newCoordinatesOrder = new Coordinates();
        newCoordinatesOrder.setLatitude(newOrderDTO.getCoordinates().getLatitude());
        newCoordinatesOrder.setLongitude(newOrderDTO.getCoordinates().getLongitude());

        newOrder.setCoordinates(newCoordinatesOrder);

        return newOrder;

    }

}
