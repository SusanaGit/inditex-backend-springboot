package com.hackathon.inditex.services;

import com.hackathon.inditex.Entities.Order;
import com.hackathon.inditex.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderAssignationsService implements IOrderAssignationsService {

    public static final String PENDING_STATUS = "PENDING";
    private final OrderRepository orderRepository;

    @Override
    public List<Order> obtainListOrdersPending() {
        return orderRepository.findByStatus(PENDING_STATUS);
    }

}
