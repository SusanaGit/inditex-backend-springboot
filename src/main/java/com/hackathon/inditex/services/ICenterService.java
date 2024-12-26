package com.hackathon.inditex.services;

import com.hackathon.inditex.Entities.Center;
import com.hackathon.inditex.dtos.CenterDTO;

import java.util.List;

public interface ICenterService {

    String saveCenter(Center newCenter);

    List<Center> readCenters();

    String updateCenter(Long idCenterToUpdate, CenterDTO updatedCenterDTO);

    String deleteCenter(Long idCenterToDelete);

}
