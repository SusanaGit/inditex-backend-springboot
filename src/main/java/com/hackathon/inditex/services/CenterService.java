package com.hackathon.inditex.services;

import com.hackathon.inditex.Entities.Center;
import com.hackathon.inditex.Entities.Coordinates;
import com.hackathon.inditex.constants.ExceptionMessageConstants;
import com.hackathon.inditex.dtos.CenterDTO;
import com.hackathon.inditex.exceptions.CenterNotFoundException;
import com.hackathon.inditex.exceptions.CoordinatesExistException;
import com.hackathon.inditex.exceptions.CurrentLoadMoreThanMaxCapacityException;
import com.hackathon.inditex.repositories.CenterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;


@Service
@RequiredArgsConstructor
public class CenterService implements ICenterService {

    public static final String LOGISTICS_CENTER_UPDATED_SUCCESSFULLY = "Logistics center updated successfully.";
    public static final String LOGISTICS_CENTER_CREATED_SUCCESSFULLY = "Logistics center created successfully.";
    public static final String LOGISTICS_CENTER_DELETED_SUCCESSFULLY = "Logistics center deleted successfully.";


    private final CenterRepository centerRepository;

    @Override
    public String saveCenter(Center newCenter) {
        validateNewCenter(newCenter);
        centerRepository.save(newCenter);
        return LOGISTICS_CENTER_CREATED_SUCCESSFULLY;
    }

    @Override
    public List<Center> readCenters() {
        return centerRepository.findAll();
    }

    @Override
    public String updateCenter(Long idCenterToUpdate, CenterDTO updatedCenterDTO) {

        Center currentCenter = obtainCenterById(idCenterToUpdate);

        updateCenterValues(currentCenter, updatedCenterDTO);

        centerRepository.save(currentCenter);

        return LOGISTICS_CENTER_UPDATED_SUCCESSFULLY;

    }

    @Override
    public String deleteCenter(Long idCenterToDelete) {
        centerRepository.deleteById(idCenterToDelete);
        return LOGISTICS_CENTER_DELETED_SUCCESSFULLY;
    }

    private void validateNewCenter(Center newCenter) {

        if (centerRepository.existsByCoordinatesLatitudeAndCoordinatesLongitude(
                newCenter.getCoordinates().getLatitude(), newCenter.getCoordinates().getLongitude())) {
            throw new CoordinatesExistException(ExceptionMessageConstants.THERE_IS_ALREADY_A_LOGISTICS_CENTER_IN_THAT_POSITION);
        }

        if (newCenter.getCurrentLoad() > newCenter.getMaxCapacity()) {
            throw new CurrentLoadMoreThanMaxCapacityException(ExceptionMessageConstants.CURRENT_LOAD_CANNOT_EXCEED_MAX_CAPACITY);
        }
    }

    private Center obtainCenterById(Long idCenter) {
        return centerRepository.findById(idCenter)
                .orElseThrow(() -> new CenterNotFoundException(ExceptionMessageConstants.CENTER_NOT_FOUND));
    }

    private void updateCenterValues(Center currentCenter, CenterDTO updatedCenterDTO) {

        updateIfNotNull(updatedCenterDTO.getName(), currentCenter::setName);
        updateIfNotNull(updatedCenterDTO.getCapacity(), currentCenter::setCapacity);
        updateIfNotNull(updatedCenterDTO.getStatus(), currentCenter::setStatus);
        updateIfNotNull(updatedCenterDTO.getMaxCapacity(), currentCenter::setMaxCapacity);
        updateIfNotNull(updatedCenterDTO.getCurrentLoad(), currentCenter::setCurrentLoad);

        updateCurrentLoadCenter(currentCenter, updatedCenterDTO);

        updateCoordinatesCenter(currentCenter, updatedCenterDTO);
    }

    private void updateCurrentLoadCenter(Center currentCenter, CenterDTO updatedCenterDTO) {
        if (updatedCenterDTO.getCurrentLoad() != null) {

            validateCurrentLoad(updatedCenterDTO.getCurrentLoad(), currentCenter.getMaxCapacity());

            currentCenter.setCurrentLoad(updatedCenterDTO.getCurrentLoad());
        }
    }

    private void validateCurrentLoad(Integer currentLoad, Integer maxCapacity) {
        if (currentLoad > maxCapacity) {
            throw new CurrentLoadMoreThanMaxCapacityException(ExceptionMessageConstants.CURRENT_LOAD_CANNOT_EXCEED_MAX_CAPACITY);
        }
    }

    private void updateCoordinatesCenter(Center currentCenter, CenterDTO updatedCenterDTO) {
        if (updatedCenterDTO.getCoordinates() != null) {

            Coordinates updatedCoordinates = updatedCenterDTO.getCoordinates();
            Coordinates currentCoordinates = currentCenter.getCoordinates();

            updateIfNotNull(updatedCoordinates.getLongitude(), currentCoordinates::setLongitude);
            updateIfNotNull(updatedCoordinates.getLatitude(), currentCoordinates::setLatitude);

        }
    }

    private <T> void updateIfNotNull(T value, Consumer<T> setter) {
        if (value != null) {
            setter.accept(value);
        }
    }

}