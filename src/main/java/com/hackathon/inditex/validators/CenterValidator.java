package com.hackathon.inditex.validators;

import com.hackathon.inditex.constants.ExceptionMessageConstants;
import com.hackathon.inditex.entities.Center;
import com.hackathon.inditex.exceptions.CoordinatesExistException;
import com.hackathon.inditex.exceptions.CurrentLoadMoreThanMaxCapacityException;
import com.hackathon.inditex.repositories.CenterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CenterValidator {

    public final CenterRepository centerRepository;

    public void validateNewCenter(Center newCenter) {
        validateCoordinatesNewCenter(newCenter);
        validateCurrentLoadNewCenter(newCenter);
    }

    private void validateCoordinatesNewCenter(Center newCenter) {
        if (centerRepository.existsByCoordinatesLatitudeAndCoordinatesLongitude(
                newCenter.getCoordinates().getLatitude(), newCenter.getCoordinates().getLongitude())) {
            throw new CoordinatesExistException(ExceptionMessageConstants.THERE_IS_ALREADY_A_LOGISTICS_CENTER_IN_THAT_POSITION);
        }
    }

    private void validateCurrentLoadNewCenter(Center newCenter) {
        if (newCenter.getCurrentLoad() > newCenter.getMaxCapacity()) {
            throw new CurrentLoadMoreThanMaxCapacityException(ExceptionMessageConstants.CURRENT_LOAD_CANNOT_EXCEED_MAX_CAPACITY);
        }
    }

}
