package com.hackathon.inditex.services;

import com.hackathon.inditex.Entities.Center;
import com.hackathon.inditex.Entities.Order;
import com.hackathon.inditex.dtos.ProcessedOrderDTO;
import com.hackathon.inditex.repositories.CenterRepository;
import com.hackathon.inditex.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderAssignationsService implements IOrderAssignationsService {

    public static final String PENDING_STATUS = "PENDING";
    private final CenterRepository centerRepository;
    private final OrderRepository orderRepository;

    @Override
    public List<ProcessedOrderDTO> assignCenterToOrders() {

        List<Order> ordersPending = obtainListOrdersPending();
        List<ProcessedOrderDTO> listProcessedOrdersDTO = new ArrayList<>();

        for (Order order: ordersPending) {

            ProcessedOrderDTO processedOrderDTO = new ProcessedOrderDTO();

            String sizeOrder = order.getSize();

            List<Center> listCentersByCapacity = centerRepository.findByCapacity(sizeOrder);

            if (listCentersByCapacity.isEmpty()) {

                processedOrderDTO.setMessage("No available centers support the order type.");
                processedOrderDTO.setDistance(null);
                processedOrderDTO.setAssignedLogisticsCenter(null);

            } else {

                List<Center> availableCenters = centerRepository.findAvailableCenters(sizeOrder);

                if (availableCenters.isEmpty()) {

                    processedOrderDTO.setMessage("All centers are at maximum capacity.");
                    processedOrderDTO.setDistance(null);
                    processedOrderDTO.setAssignedLogisticsCenter(null);

                } else {
                    processedOrderDTO.setDistance(null);
                    processedOrderDTO.setAssignedLogisticsCenter(null);

                }

            }

            processedOrderDTO.setOrderId(order.getId());
            processedOrderDTO.setStatus(order.getStatus());

            listProcessedOrdersDTO.add(processedOrderDTO);
        }

        return listProcessedOrdersDTO;

    }

    private List<Order> obtainListOrdersPending() {
        return orderRepository.findByStatus(PENDING_STATUS);
    }

}