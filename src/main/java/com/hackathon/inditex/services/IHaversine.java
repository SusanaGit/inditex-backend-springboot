package com.hackathon.inditex.services;

import com.hackathon.inditex.entities.Coordinates;

public interface IHaversine {
    double calculateHaversineVariable(
            double latitudeDifferenceRadians,
            double longitudeDifferenceRadians,
            Coordinates centerCoordinates,
            Coordinates orderCoordinates
    );

    double calculateSinSquare(double angle);

    double calculateCosine(double degree);

    double calculateLatitudeDifferenceRadians(Coordinates centerCoordinates, Coordinates orderCoordinates);

    double calculateLongitudeDifferenceRadians(Coordinates centerCoordinates, Coordinates orderCoordinates);
}
