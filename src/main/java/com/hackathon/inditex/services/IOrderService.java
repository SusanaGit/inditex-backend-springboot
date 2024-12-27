package com.hackathon.inditex.services;

import com.hackathon.inditex.Entities.Order;

public interface IOrderService {

    Order saveOrder(Order newOrder);

}
