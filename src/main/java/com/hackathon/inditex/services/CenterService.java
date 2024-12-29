package com.hackathon.inditex.services;

import com.hackathon.inditex.Entities.Center;
import com.hackathon.inditex.constants.ExceptionMessageConstants;
import com.hackathon.inditex.dtos.CenterDTO;
import com.hackathon.inditex.exceptions.CenterNotFoundException;
import com.hackathon.inditex.exceptions.CoordinatesExistException;
import com.hackathon.inditex.exceptions.CurrentLoadMoreThanMaxCapacityException;
import com.hackathon.inditex.repositories.CenterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


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

        if (updatedCenterDTO.getName() != null) {
            currentCenter.setName(updatedCenterDTO.getName());
        }

        if (updatedCenterDTO.getCapacity() != null) {
            currentCenter.setCapacity(updatedCenterDTO.getCapacity());
        }

        if (updatedCenterDTO.getStatus() != null) {
            currentCenter.setStatus(updatedCenterDTO.getStatus());
        }

        if (updatedCenterDTO.getMaxCapacity() != null) {
            currentCenter.setMaxCapacity(updatedCenterDTO.getMaxCapacity());
        }

        updateCurrentLoadCenter(currentCenter, updatedCenterDTO);

        updateCoordinatesCenter(currentCenter, updatedCenterDTO);
    }

    private void updateCurrentLoadCenter(Center currentCenter, CenterDTO updatedCenterDTO) {
        if (updatedCenterDTO.getCurrentLoad() != null) {

            if (updatedCenterDTO.getCurrentLoad() > currentCenter.getMaxCapacity()) {
                throw new CurrentLoadMoreThanMaxCapacityException(ExceptionMessageConstants.CURRENT_LOAD_CANNOT_EXCEED_MAX_CAPACITY);
            }

            currentCenter.setCurrentLoad(updatedCenterDTO.getCurrentLoad());
        }
    }

    private void updateCoordinatesCenter(Center currentCenter, CenterDTO updatedCenterDTO) {
        if (updatedCenterDTO.getCoordinates() != null) {

            if (updatedCenterDTO.getCoordinates().getLongitude() != null) {
                currentCenter.getCoordinates().setLongitude(updatedCenterDTO.getCoordinates().getLongitude());
            }

            if (updatedCenterDTO.getCoordinates().getLatitude() != null) {
                currentCenter.getCoordinates().setLatitude(updatedCenterDTO.getCoordinates().getLatitude());
            }
        }
    }
}