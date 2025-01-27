package com.hackathon.inditex.services;

import com.hackathon.inditex.constants.ExceptionMessageConstants;
import com.hackathon.inditex.entities.Center;
import com.hackathon.inditex.entities.Order;
import com.hackathon.inditex.exceptions.CenterNotFoundException;
import com.hackathon.inditex.repositories.CenterRepository;
import com.hackathon.inditex.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AssignCenterToOrderService {

    public static final String ASSIGNED_STATUS = "ASSIGNED";

    private final DistanceOrderCenterService distanceOrderCenterService;
    private final OrderRepository orderRepository;
    private final CenterRepository centerRepository;

    public double assignBestCenter(Order order, List<Center> availableCenters) {
        Center bestCenter = findBestCenter(order, availableCenters);
        assignCenterToTheOrder(order, bestCenter);
        return calculateDistance(bestCenter, order);
    }

    private Center findBestCenter(Order order, List<Center> availableCenters) {
        return availableCenters.stream()
                .min((center1, center2) -> Double.compare(
                        distanceOrderCenterService.obtainDistanceBetweenCenterAndOrder(center1, order),
                        distanceOrderCenterService.obtainDistanceBetweenCenterAndOrder(center2, order)
                ))
                .orElseThrow(() -> new CenterNotFoundException(ExceptionMessageConstants.CENTER_NOT_FOUND));
    }

    private double calculateDistance(Center center, Order order) {
        return distanceOrderCenterService.obtainDistanceBetweenCenterAndOrder(center, order);
    }

    private void assignCenterToTheOrder(Order order, Center center) {
        order.setAssignedCenter(center.getName());
        order.setStatus(ASSIGNED_STATUS);
        orderRepository.save(order);
        incrementCurrentLoadCenter(center);
    }

    private void incrementCurrentLoadCenter(Center bestCenter) {
        bestCenter.setCurrentLoad(bestCenter.getCurrentLoad() + 1);
        centerRepository.save(bestCenter);
    }

}
