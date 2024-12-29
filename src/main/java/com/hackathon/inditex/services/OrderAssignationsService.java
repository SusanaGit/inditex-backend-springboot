package com.hackathon.inditex.services;

import com.hackathon.inditex.Entities.Center;
import com.hackathon.inditex.Entities.Order;
import com.hackathon.inditex.constants.MessageConstants;
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

    @Override
    public List<ProcessedOrderDTO> assignCenterToOrders() {

        List<Order> ordersPending = obtainListOrdersPending();
        List<ProcessedOrderDTO> listProcessedOrdersDTO = new ArrayList<>();

        for (Order order : ordersPending) {

            ProcessedOrderDTO processedOrderDTO = new ProcessedOrderDTO();

            String sizeOrder = order.getSize();

            List<Center> listCentersByCapacity = centerRepository.findByCapacity(sizeOrder);

            if (listCentersByCapacity.isEmpty()) {

                processedOrderDTO.setMessage(NO_AVAILABLE_CENTERS_SUPPORT_THE_ORDER_TYPE);
                processedOrderDTO.setDistance(null);
                processedOrderDTO.setAssignedLogisticsCenter(null);

            } else {

                List<Center> availableCenters = centerRepository.findAvailableCenters(listCentersByCapacity);

                if (availableCenters.isEmpty()) {

                    processedOrderDTO.setMessage(ALL_CENTERS_ARE_AT_MAXIMUM_CAPACITY);
                    processedOrderDTO.setDistance(null);
                    processedOrderDTO.setAssignedLogisticsCenter(null);

                } else {

                    double bestDistance = obtainTheBestDistance(availableCenters, order);

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

    private double obtainTheBestDistance(List<Center> availableCenters, Order order) {

        Center bestCenter = null;

        double bestDistance = Double.MAX_VALUE;

        for (Center center : availableCenters) {

            double distanceBetweenCenterAndOrder = obtainDistanceBetweenCenterAndOrder(center, order);

            if (distanceBetweenCenterAndOrder < bestDistance) {
                bestDistance = distanceBetweenCenterAndOrder;
                bestCenter = center;
            }

        }

        if (bestCenter == null) {
            throw new CenterNotFoundException(MessageConstants.CENTER_NOT_FOUND);
        }

        assignCenterToTheOrder(order, bestCenter.getName());

        incrementCurrentLoadCenter(bestCenter);

        return bestDistance;

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

        double latitudeCenter = center.getCoordinates().getLatitude();
        double latitudeOrder = order.getCoordinates().getLatitude();

        double longitudeCenter = center.getCoordinates().getLongitude();
        double longitudeOrder = order.getCoordinates().getLongitude();

        final int EARTH_RADIUS = 6371;

        double latitudeBetweenCenterAndOrderRadians = Math.toRadians(latitudeOrder - latitudeCenter);
        double longitudeBetweenCenterAndOrderRadians = Math.toRadians(longitudeCenter - longitudeOrder);

        double a = Math.sin(latitudeBetweenCenterAndOrderRadians / 2) * Math.sin(latitudeBetweenCenterAndOrderRadians / 2) +
                Math.cos(Math.toRadians(latitudeCenter)) * Math.cos(Math.toRadians(latitudeOrder)) *
                        Math.sin(longitudeBetweenCenterAndOrderRadians / 2) * Math.sin(longitudeBetweenCenterAndOrderRadians / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS * c;

    }

    private List<Order> obtainListOrdersPending() {
        return orderRepository.findByStatus(PENDING_STATUS);
    }
}