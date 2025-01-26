package com.hackathon.inditex.mappers;

import com.hackathon.inditex.entities.Center;
import com.hackathon.inditex.dtos.CenterDTO;

public interface ICenterMapper {
    Center centerDTOtoCenter(CenterDTO newCenterDTO);
}