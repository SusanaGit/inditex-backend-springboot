package com.hackathon.inditex.updates;

import com.hackathon.inditex.dtos.CenterDTO;
import com.hackathon.inditex.entities.Center;
import com.hackathon.inditex.entities.Coordinates;
import com.hackathon.inditex.utils.Utils;
import org.springframework.stereotype.Component;

@Component
public class UpdateCoordinatesCenter {

    public void updateCoordinatesCenter(Center currentCenter, CenterDTO updatedCenterDTO) {
        if (updatedCenterDTO.getCoordinates() != null) {
            Coordinates updatedCoordinates = updatedCenterDTO.getCoordinates();
            Coordinates currentCoordinates = currentCenter.getCoordinates();
            Utils.updateIfNotNull(updatedCoordinates.getLongitude(), currentCoordinates::setLongitude);
            Utils.updateIfNotNull(updatedCoordinates.getLatitude(), currentCoordinates::setLatitude);
        }
    }
}
