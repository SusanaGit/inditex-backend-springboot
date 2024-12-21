package com.hackathon.inditex.services;

import com.hackathon.inditex.Entities.Center;
import com.hackathon.inditex.repositories.CenterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CenterService {

    private final CenterRepository centerRepository;

    public Center testCenterService() {
        return centerRepository.findAll().get(0);
    }

}
