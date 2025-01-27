package com.hackathon.inditex.services;

import com.hackathon.inditex.dtos.ProcessedOrderDTO;
import com.hackathon.inditex.entities.Center;
import com.hackathon.inditex.entities.Order;
import com.hackathon.inditex.repositories.CenterRepository;
import com.hackathon.inditex.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderAssignationsService implements IOrderAssignationsService {

    public static final String ALL_CENTERS_ARE_AT_MAXIMUM_CAPACITY = "All centers are at maximum capacity.";
    public static final String NO_AVAILABLE_CENTERS_SUPPORT_THE_ORDER_TYPE = "No available centers support the order type.";
    public static final String PENDING_STATUS = "PENDING";

    private final CenterRepository centerRepository;
    private final OrderRepository orderRepository;
    private final AssignCenterToOrderService assignCenterService;

    @Override
    public List<ProcessedOrderDTO> assignCenterToOrders() {

        List<Order> ordersPending = orderRepository.findByStatus(PENDING_STATUS);
        List<ProcessedOrderDTO> listProcessedOrdersDTO = new ArrayList<>();

        for (Order order : ordersPending) {

            ProcessedOrderDTO processedOrderDTO = new ProcessedOrderDTO();

            String sizeOrder = order.getSize();

            List<Center> listCentersByCapacity = centerRepository.findByCapacity(sizeOrder);

            if (listCentersByCapacity.isEmpty()) {


                processedOrderDTO.setMessage(NO_AVAILABLE_CENTERS_SUPPORT_THE_ORDER_TYPE);

            } else {

                List<Center> availableCenters = centerRepository.findAvailableCenters(listCentersByCapacity);

                if (availableCenters.isEmpty()) {

                    processedOrderDTO.setMessage(ALL_CENTERS_ARE_AT_MAXIMUM_CAPACITY);

                } else {

                    double bestDistance = assignCenterService.assignBestCenter(order, availableCenters);

                    processedOrderDTO.setDistance(bestDistance);
                    processedOrderDTO.setAssignedLogisticsCenter(order.getAssignedCenter());

                }

            }

            processedOrderDTO.setOrderId(order.getId());
            processedOrderDTO.setStatus(order.getStatus());

            listProcessedOrdersDTO.add(processedOrderDTO);
        }

        return listProcessedOrdersDTO;

    }

}