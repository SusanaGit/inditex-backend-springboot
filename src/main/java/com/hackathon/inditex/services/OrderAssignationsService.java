package com.hackathon.inditex.services;

import com.hackathon.inditex.entities.Center;
import com.hackathon.inditex.entities.Order;
import com.hackathon.inditex.constants.ExceptionMessageConstants;
import com.hackathon.inditex.dtos.ProcessedOrderDTO;
import com.hackathon.inditex.exceptions.CenterNotFoundException;
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
    public static final String ASSIGNED_STATUS = "ASSIGNED";

    private final CenterRepository centerRepository;
    private final OrderRepository orderRepository;
    private final HaversineService haversineService;

    @Override
    public List<ProcessedOrderDTO> assignCenterToOrders() {

        List<Order> ordersPending = orderRepository.findByStatus(OrderAssignationsService.PENDING_STATUS);
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

                    double bestDistance = assignBestCenter(order, availableCenters);

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

    private double assignBestCenter(Order order, List<Center> availableCenters) {
        Center bestCenter = findBestCenter(order, availableCenters);
        assignCenterToTheOrder(order, bestCenter.getName());
        incrementCurrentLoadCenter(bestCenter);
        return obtainDistanceBetweenCenterAndOrder(bestCenter, order);
    }

    private Center findBestCenter(Order order, List<Center> availableCenters) {
        return availableCenters.stream()
                .min((center1, center2) -> Double.compare(
                        obtainDistanceBetweenCenterAndOrder(center1, order),
                        obtainDistanceBetweenCenterAndOrder(center2, order)
                ))
                .orElseThrow(() -> new CenterNotFoundException(ExceptionMessageConstants.CENTER_NOT_FOUND));
    }

    private void incrementCurrentLoadCenter(Center bestCenter) {
        bestCenter.setCurrentLoad(bestCenter.getCurrentLoad() + 1);
        centerRepository.save(bestCenter);
    }

    private void assignCenterToTheOrder(Order order, String nameBestCenter) {

        order.setAssignedCenter(nameBestCenter);

        order.setStatus(ASSIGNED_STATUS);

        orderRepository.save(order);

    }

    private double obtainDistanceBetweenCenterAndOrder(Center center, Order order) {

        final int EARTH_RADIUS = 6371;

        double latitudeDifferenceRadians = haversineService.calculateLatitudeDifferenceRadians(center.getCoordinates(), order.getCoordinates());
        double longitudeDifferenceRadians = haversineService.calculateLongitudeDifferenceRadians(center.getCoordinates(), order.getCoordinates());

        double a = haversineService.calculateHaversineVariable(latitudeDifferenceRadians, longitudeDifferenceRadians, center.getCoordinates(), order.getCoordinates());

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS * c;
    }
}