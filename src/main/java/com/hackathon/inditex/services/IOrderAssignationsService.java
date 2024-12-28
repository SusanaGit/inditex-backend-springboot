package com.hackathon.inditex.services;

import com.hackathon.inditex.dtos.ProcessedOrderDTO;

import java.util.List;

public interface IOrderAssignationsService {

    List<ProcessedOrderDTO> assignCenterToOrders();
}
