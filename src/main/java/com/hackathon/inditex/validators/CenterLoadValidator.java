package com.hackathon.inditex.validators;

import com.hackathon.inditex.constants.ExceptionMessageConstants;
import com.hackathon.inditex.exceptions.CurrentLoadMoreThanMaxCapacityException;
import org.springframework.stereotype.Component;

@Component
public class CenterLoadValidator {

    public void validateCurrentLoad(Integer currentLoad, Integer maxCapacity) {
        if (currentLoad > maxCapacity) {
            throw new CurrentLoadMoreThanMaxCapacityException(ExceptionMessageConstants.CURRENT_LOAD_CANNOT_EXCEED_MAX_CAPACITY);
        }
    }

}
