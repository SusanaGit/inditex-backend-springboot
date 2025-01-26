package com.hackathon.inditex.services;

import com.hackathon.inditex.entities.Order;

import java.util.List;

public interface IOrderService {

    Order saveOrder(Order newOrder);

    List<Order> readOrders();
}
