package com.hackathon.inditex.updates;

import com.hackathon.inditex.dtos.CenterDTO;
import com.hackathon.inditex.entities.Center;
import com.hackathon.inditex.validators.CenterLoadValidator;
import org.springframework.stereotype.Component;

@Component
public class UpdateCurrentLoadCenter {
    private final CenterLoadValidator centerLoadValidator;

    public UpdateCurrentLoadCenter(CenterLoadValidator centerLoadValidator) {
        this.centerLoadValidator = centerLoadValidator;
    }

    public void updateCurrentLoadCenter(Center currentCenter, CenterDTO updatedCenterDTO) {
        if (updatedCenterDTO.getCurrentLoad() != null) {
            centerLoadValidator.validateCurrentLoad(updatedCenterDTO.getCurrentLoad(), currentCenter.getMaxCapacity());
            currentCenter.setCurrentLoad(updatedCenterDTO.getCurrentLoad());
        }
    }
}
