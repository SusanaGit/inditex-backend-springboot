package com.hackathon.inditex.services;

import com.hackathon.inditex.dtos.CenterDTO;
import com.hackathon.inditex.entities.Center;
import com.hackathon.inditex.entities.Coordinates;
import com.hackathon.inditex.utils.Utils;
import com.hackathon.inditex.validators.CenterLoadValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CenterUpdateService {

    private final CenterLoadValidator centerLoadValidator;

    public void updateCenterValues(Center currentCenter, CenterDTO updatedCenterDTO) {
        updateMainValuesCenter(currentCenter, updatedCenterDTO);
        updateCurrentLoadCenter(currentCenter, updatedCenterDTO.getCurrentLoad(), currentCenter.getMaxCapacity());
        updateCoordinatesCenter(currentCenter, updatedCenterDTO.getCoordinates());
    }

    private void updateMainValuesCenter(Center currentCenter, CenterDTO updatedCenterDTO) {
        Utils.ifNotNull(updatedCenterDTO.getName(), currentCenter::setName);
        Utils.ifNotNull(updatedCenterDTO.getCapacity(), currentCenter::setCapacity);
        Utils.ifNotNull(updatedCenterDTO.getStatus(), currentCenter::setStatus);
        Utils.ifNotNull(updatedCenterDTO.getMaxCapacity(), currentCenter::setMaxCapacity);
    }

    private void updateCurrentLoadCenter(Center currentCenter, Integer newLoad, Integer maxCapacity) {
        Utils.ifNotNull(newLoad, load -> validateAndSetLoad(currentCenter, load, maxCapacity));
    }

    private void validateAndSetLoad(Center currentCenter, Integer newLoad, Integer maxCapacity) {
        centerLoadValidator.validateCurrentLoad(newLoad, maxCapacity);
        currentCenter.setCurrentLoad(newLoad);
    }

    private void updateCoordinatesCenter(Center currentCenter, Coordinates updatedCoordinates) {
        Utils.ifNotNull(updatedCoordinates, coords -> updateCoordinateValues(currentCenter.getCoordinates(), coords));
    }

    private void updateCoordinateValues(Coordinates currentCoordinates, Coordinates updatedCoordinates) {
        Utils.ifNotNull(updatedCoordinates.getLongitude(), currentCoordinates::setLongitude);
        Utils.ifNotNull(updatedCoordinates.getLatitude(), currentCoordinates::setLatitude);
    }
}
