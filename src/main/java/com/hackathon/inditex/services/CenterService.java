package com.hackathon.inditex.services;

import com.hackathon.inditex.Entities.Center;
import com.hackathon.inditex.exceptions.CoordinatesExistException;
import com.hackathon.inditex.exceptions.CurrentLoadMoreThanMaxCapacityException;
import com.hackathon.inditex.repositories.CenterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CenterService {

    private final CenterRepository centerRepository;

    public void saveCenter(Center newCenter) {

        if (centerRepository.existsByCoordinatesLatitudeAndCoordinatesLongitude(
                newCenter.getCoordinates().getLatitude(), newCenter.getCoordinates().getLongitude())) {

            throw new CoordinatesExistException("There is already a logistics center in that position.");

        } else if (newCenter.getCurrentLoad() > newCenter.getMaxCapacity()) {

            throw new CurrentLoadMoreThanMaxCapacityException("Current load cannot exceed max capacity.");

        } else {

            centerRepository.save(newCenter);

            System.out.println("Logistics center created successfully.");

        }

    }
}
