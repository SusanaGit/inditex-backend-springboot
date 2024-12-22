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

    public static final String THERE_IS_ALREADY_A_LOGISTICS_CENTER_IN_THAT_POSITION = "There is already a logistics center in that position.";
    public static final String CURRENT_LOAD_CANNOT_EXCEED_MAX_CAPACITY = "Current load cannot exceed max capacity.";

    private final CenterRepository centerRepository;

    public void saveCenter(Center newCenter) {

        if (centerRepository.existsByCoordinatesLatitudeAndCoordinatesLongitude(
                newCenter.getCoordinates().getLatitude(), newCenter.getCoordinates().getLongitude())) {

            throw new CoordinatesExistException(THERE_IS_ALREADY_A_LOGISTICS_CENTER_IN_THAT_POSITION);

        } else if (newCenter.getCurrentLoad() > newCenter.getMaxCapacity()) {

            throw new CurrentLoadMoreThanMaxCapacityException(CURRENT_LOAD_CANNOT_EXCEED_MAX_CAPACITY);

        } else {

            centerRepository.save(newCenter);

            System.out.println("Logistics center created successfully.");

        }

    }
}
