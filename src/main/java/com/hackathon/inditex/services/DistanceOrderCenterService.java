package com.hackathon.inditex.services;

import com.hackathon.inditex.entities.Center;
import com.hackathon.inditex.entities.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DistanceOrderCenterService {

    private final HaversineService haversineService;

    public double obtainDistanceBetweenCenterAndOrder(Center center, Order order) {

        final int EARTH_RADIUS = 6371;

        double latitudeDifferenceRadians = haversineService.calculateLatitudeDifferenceRadians(center.getCoordinates(), order.getCoordinates());
        double longitudeDifferenceRadians = haversineService.calculateLongitudeDifferenceRadians(center.getCoordinates(), order.getCoordinates());

        double a = haversineService.calculateHaversineVariable(latitudeDifferenceRadians, longitudeDifferenceRadians, center.getCoordinates(), order.getCoordinates());

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS * c;
    }

}
