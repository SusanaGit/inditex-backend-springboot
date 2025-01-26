package com.hackathon.inditex.updates;

import com.hackathon.inditex.dtos.CenterDTO;
import com.hackathon.inditex.entities.Center;
import com.hackathon.inditex.utils.Utils;
import org.springframework.stereotype.Component;

@Component
public class UpdateMainValuesCenter {
    public void updateMainValuesCenter(Center currentCenter, CenterDTO updatedCenterDTO) {
        Utils.updateIfNotNull(updatedCenterDTO.getName(), currentCenter::setName);
        Utils.updateIfNotNull(updatedCenterDTO.getCapacity(), currentCenter::setCapacity);
        Utils.updateIfNotNull(updatedCenterDTO.getStatus(), currentCenter::setStatus);
        Utils.updateIfNotNull(updatedCenterDTO.getMaxCapacity(), currentCenter::setMaxCapacity);
    }
}
