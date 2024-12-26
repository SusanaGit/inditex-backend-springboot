package com.hackathon.inditex.services;

import com.hackathon.inditex.Entities.Center;
import com.hackathon.inditex.Entities.Coordinates;
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
public class CenterService {

    public static final String THERE_IS_ALREADY_A_LOGISTICS_CENTER_IN_THAT_POSITION = "There is already a logistics center in that position.";
    public static final String CURRENT_LOAD_CANNOT_EXCEED_MAX_CAPACITY = "Current load cannot exceed max capacity.";
    public static final String LOGISTICS_CENTER_CREATED_SUCCESSFULLY = "Logistics center created successfully.";
    public static final String LOGISTICS_CENTER_DELETED_SUCCESSFULLY = "Logistics center deleted successfully.";
    public static final String CENTER_NOT_FOUND = "Center not found.";
    public static final String LOGISTICS_CENTER_UPDATED_SUCCESSFULLY = "Logistics center updated successfully.";

    private final CenterRepository centerRepository;

    public String saveCenter(Center newCenter) {

        if (centerRepository.existsByCoordinatesLatitudeAndCoordinatesLongitude(
                newCenter.getCoordinates().getLatitude(), newCenter.getCoordinates().getLongitude())) {

            throw new CoordinatesExistException(THERE_IS_ALREADY_A_LOGISTICS_CENTER_IN_THAT_POSITION);

        } else if (newCenter.getCurrentLoad() > newCenter.getMaxCapacity()) {

            throw new CurrentLoadMoreThanMaxCapacityException(CURRENT_LOAD_CANNOT_EXCEED_MAX_CAPACITY);

        } else {

            centerRepository.save(newCenter);

            return LOGISTICS_CENTER_CREATED_SUCCESSFULLY;

        }
    }

    public List<Center> readCenters() {

        return centerRepository.findAll();

    }

    public String updateCenter(Long idCenterToUpdate, CenterDTO updatedCenterDTO) {

        if (centerRepository.existsById(idCenterToUpdate)) {

            Center currentCenter = centerRepository.getReferenceById(idCenterToUpdate);

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

            if (updatedCenterDTO.getCurrentLoad() != null) {
                currentCenter.setCurrentLoad(updatedCenterDTO.getCurrentLoad());
            }

            if (updatedCenterDTO.getCoordinates() != null) {

                if (updatedCenterDTO.getCoordinates().getLongitude() != null) {
                    currentCenter.getCoordinates().setLongitude(updatedCenterDTO.getCoordinates().getLongitude());
                }

                if (updatedCenterDTO.getCoordinates().getLatitude() != null) {
                    currentCenter.getCoordinates().setLatitude(updatedCenterDTO.getCoordinates().getLatitude());
                }

                centerRepository.save(currentCenter);

            }

            return LOGISTICS_CENTER_UPDATED_SUCCESSFULLY;

        } else {

            throw new CenterNotFoundException(CENTER_NOT_FOUND);

        }
    }

    private Coordinates coordinates;

    public String deleteCenter(Long idCenterToDelete) {
        centerRepository.deleteById(idCenterToDelete);
        return LOGISTICS_CENTER_DELETED_SUCCESSFULLY;
    }
}
