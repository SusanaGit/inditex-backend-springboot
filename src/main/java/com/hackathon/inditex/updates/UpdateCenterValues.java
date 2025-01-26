package com.hackathon.inditex.updates;

import com.hackathon.inditex.dtos.CenterDTO;
import com.hackathon.inditex.entities.Center;
import org.springframework.stereotype.Component;

@Component
public class UpdateCenterValues {

    private final UpdateMainValuesCenter updateMainValuesCenter;
    private final UpdateCurrentLoadCenter updateCurrentLoadCenter;
    private final UpdateCoordinatesCenter updateCoordinatesCenter;

    public UpdateCenterValues(UpdateMainValuesCenter updateMainValuesCenter, UpdateCurrentLoadCenter updateCurrentLoadCenter, UpdateCoordinatesCenter updateCoordinatesCenter) {
        this.updateMainValuesCenter = updateMainValuesCenter;
        this.updateCurrentLoadCenter = updateCurrentLoadCenter;
        this.updateCoordinatesCenter = updateCoordinatesCenter;
    }

    public void updateCenterValues(Center currentCenter, CenterDTO updatedCenterDTO) {
        updateMainValuesCenter.updateMainValuesCenter(currentCenter, updatedCenterDTO);
        updateCurrentLoadCenter.updateCurrentLoadCenter(currentCenter, updatedCenterDTO);
        updateCoordinatesCenter.updateCoordinatesCenter(currentCenter, updatedCenterDTO);
    }

}
