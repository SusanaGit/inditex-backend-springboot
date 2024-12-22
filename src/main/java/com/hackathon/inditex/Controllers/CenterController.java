package com.hackathon.inditex.Controllers;

import com.hackathon.inditex.dtos.CenterDTO;
import com.hackathon.inditex.services.CenterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/centers")
@RequiredArgsConstructor
public class CenterController {

    private final CenterService centerService;

    @PostMapping
    public void createNewCenter(@RequestBody CenterDTO newCenterDTO) {

    }

}
