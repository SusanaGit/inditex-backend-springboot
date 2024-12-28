package com.hackathon.inditex.services;

import com.hackathon.inditex.Entities.Order;

import java.util.List;

public interface IOrderAssignationsService {

    List<Order> obtainListOrdersPending();
}
