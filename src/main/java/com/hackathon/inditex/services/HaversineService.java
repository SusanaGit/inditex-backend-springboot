package com.hackathon.inditex.services;

import com.hackathon.inditex.entities.Coordinates;
import org.springframework.stereotype.Service;

@Service
public class HaversineService {

    double calculateHaversineVariable(
            double latitudeDifferenceRadians,
            double longitudeDifferenceRadians,
            Coordinates centerCoordinates,
            Coordinates orderCoordinates
    ) {
        double sinLatitude = calculateSinSquare(latitudeDifferenceRadians / 2);
        double sinLongitude = calculateSinSquare(longitudeDifferenceRadians / 2);
        double cosCenterLatitude = calculateCosine(centerCoordinates.getLatitude());
        double cosOrderLatitude = calculateCosine(orderCoordinates.getLatitude());

        return sinLatitude + cosCenterLatitude * cosOrderLatitude * sinLongitude;
    }

    private double calculateSinSquare(double angle) {
        return Math.sin(angle) * Math.sin(angle);
    }

    private double calculateCosine(double degree) {
        return Math.cos(Math.toRadians(degree));
    }

    double calculateLatitudeDifferenceRadians(Coordinates centerCoordinates, Coordinates orderCoordinates) {
        return Math.toRadians(orderCoordinates.getLongitude() - centerCoordinates.getLongitude());
    }

    protected double calculateLongitudeDifferenceRadians(Coordinates centerCoordinates, Coordinates orderCoordinates) {
        return Math.toRadians(centerCoordinates.getLongitude() - orderCoordinates.getLongitude());
    }

}
