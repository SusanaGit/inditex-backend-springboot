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
        updateCurrentLoadCenter(currentCenter, updatedCenterDTO);
        updateCoordinatesCenter(currentCenter, updatedCenterDTO);
    }

    public void updateCoordinatesCenter(Center currentCenter, CenterDTO updatedCenterDTO) {
        if (updatedCenterDTO.getCoordinates() != null) {
            Coordinates updatedCoordinates = updatedCenterDTO.getCoordinates();
            Coordinates currentCoordinates = currentCenter.getCoordinates();
            Utils.updateIfNotNull(updatedCoordinates.getLongitude(), currentCoordinates::setLongitude);
            Utils.updateIfNotNull(updatedCoordinates.getLatitude(), currentCoordinates::setLatitude);
        }
    }

    public void updateCurrentLoadCenter(Center currentCenter, CenterDTO updatedCenterDTO) {
        if (updatedCenterDTO.getCurrentLoad() != null) {
            centerLoadValidator.validateCurrentLoad(updatedCenterDTO.getCurrentLoad(), currentCenter.getMaxCapacity());
            currentCenter.setCurrentLoad(updatedCenterDTO.getCurrentLoad());
        }
    }

    public void updateMainValuesCenter(Center currentCenter, CenterDTO updatedCenterDTO) {
        Utils.updateIfNotNull(updatedCenterDTO.getName(), currentCenter::setName);
        Utils.updateIfNotNull(updatedCenterDTO.getCapacity(), currentCenter::setCapacity);
        Utils.updateIfNotNull(updatedCenterDTO.getStatus(), currentCenter::setStatus);
        Utils.updateIfNotNull(updatedCenterDTO.getMaxCapacity(), currentCenter::setMaxCapacity);
    }

}
