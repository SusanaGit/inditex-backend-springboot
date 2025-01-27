package com.hackathon.inditex.services;

import com.hackathon.inditex.constants.ExceptionMessageConstants;
import com.hackathon.inditex.dtos.CenterDTO;
import com.hackathon.inditex.entities.Center;
import com.hackathon.inditex.exceptions.CenterNotFoundException;
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
    private final CenterValidatorService centerValidatorService;
    private final CenterUpdateService centerUpdateService;

    @Override
    public String saveCenter(Center newCenter) {
        centerValidatorService.validateNewCenter(newCenter);
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
        centerUpdateService.updateCenterValues(currentCenter, updatedCenterDTO);
        centerRepository.save(currentCenter);
        return LOGISTICS_CENTER_UPDATED_SUCCESSFULLY;

    }

    @Override
    public String deleteCenter(Long idCenterToDelete) {
        centerRepository.deleteById(idCenterToDelete);
        return LOGISTICS_CENTER_DELETED_SUCCESSFULLY;
    }

    private Center obtainCenterById(Long idCenter) {
        return centerRepository.findById(idCenter)
                .orElseThrow(() -> new CenterNotFoundException(ExceptionMessageConstants.CENTER_NOT_FOUND));
    }
}