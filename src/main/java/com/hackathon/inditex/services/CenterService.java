package com.hackathon.inditex.services;

import com.hackathon.inditex.constants.CenterMessageConstants;
import com.hackathon.inditex.constants.ExceptionMessageConstants;
import com.hackathon.inditex.dtos.CenterDTO;
import com.hackathon.inditex.entities.Center;
import com.hackathon.inditex.exceptions.CenterNotFoundException;
import com.hackathon.inditex.repositories.CenterRepository;
import com.hackathon.inditex.validators.CenterValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CenterService implements ICenterService {

    private final CenterRepository centerRepository;
    private final CenterValidator centerValidator;
    private final CenterUpdateService centerUpdateService;

    @Override
    public String saveCenter(Center newCenter) {
        centerValidator.validateNewCenter(newCenter);
        centerRepository.save(newCenter);
        return CenterMessageConstants.LOGISTICS_CENTER_CREATED_SUCCESSFULLY;
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
        return CenterMessageConstants.LOGISTICS_CENTER_UPDATED_SUCCESSFULLY;

    }

    @Override
    public String deleteCenter(Long idCenterToDelete) {
        centerRepository.deleteById(idCenterToDelete);
        return CenterMessageConstants.LOGISTICS_CENTER_DELETED_SUCCESSFULLY;
    }

    private Center obtainCenterById(Long idCenter) {
        return centerRepository.findById(idCenter)
                .orElseThrow(() -> new CenterNotFoundException(ExceptionMessageConstants.CENTER_NOT_FOUND));
    }
}