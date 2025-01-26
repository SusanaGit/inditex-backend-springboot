package com.hackathon.inditex.mappers;

import com.hackathon.inditex.entities.Center;
import com.hackathon.inditex.entities.Coordinates;
import com.hackathon.inditex.dtos.CenterDTO;
import org.springframework.stereotype.Service;

@Service
public class CenterMapper implements ICenterMapper {

    @Override
    public Center centerDTOtoCenter(CenterDTO newCenterDTO) {

        Center newCenter = new Center();

        newCenter.setName(newCenterDTO.getName());
        newCenter.setCapacity(newCenterDTO.getCapacity());
        newCenter.setStatus(newCenterDTO.getStatus());
        newCenter.setMaxCapacity(newCenterDTO.getMaxCapacity());
        newCenter.setCurrentLoad(newCenterDTO.getCurrentLoad());

        Coordinates newCoordinates = new Coordinates();

        newCoordinates.setLatitude(newCenterDTO.getCoordinates().getLatitude());
        newCoordinates.setLongitude(newCenterDTO.getCoordinates().getLongitude());

        newCenter.setCoordinates(newCoordinates);

        return newCenter;

    }

}
