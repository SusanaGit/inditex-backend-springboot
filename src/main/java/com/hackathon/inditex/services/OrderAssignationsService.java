package com.hackathon.inditex.services;

import com.hackathon.inditex.Entities.Order;
import com.hackathon.inditex.dtos.ProcessedOrderDTO;
import com.hackathon.inditex.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderAssignationsService implements IOrderAssignationsService {

    public static final String PENDING_STATUS = "PENDING";
    private final OrderRepository orderRepository;

    @Override
    public List<ProcessedOrderDTO> assignCenterToOrders() {

        List<Order> ordersPending = obtainListOrdersPending();
        List<ProcessedOrderDTO> listProcessedOrdersDTO = new ArrayList<>();

        for (Order order: ordersPending) {
            ProcessedOrderDTO processedOrderDTO = new ProcessedOrderDTO();

            processedOrderDTO.setDistance(null);
            processedOrderDTO.setOrderId(order.getId());
            processedOrderDTO.setAssignedLogisticsCenter(null);
            processedOrderDTO.setStatus(order.getStatus());

            listProcessedOrdersDTO.add(processedOrderDTO);
        }

        return listProcessedOrdersDTO;

    }

    private List<Order> obtainListOrdersPending() {
        return orderRepository.findByStatus(PENDING_STATUS);
    }

}