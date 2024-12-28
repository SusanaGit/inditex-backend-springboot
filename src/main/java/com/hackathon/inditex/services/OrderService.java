package com.hackathon.inditex.services;

import com.hackathon.inditex.Entities.Order;
import com.hackathon.inditex.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService implements IOrderService {

    private final OrderRepository orderRepository;

    @Override
    public Order saveOrder(Order newOrder) {
        return orderRepository.save(newOrder);
    }

    @Override
    public List<Order> readOrders() {
        return orderRepository.findAll();
    }
}
