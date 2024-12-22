package com.hackathon.inditex.Controllers;

import com.hackathon.inditex.Entities.Center;
import com.hackathon.inditex.dtos.CenterDTO;
import com.hackathon.inditex.dtos.ResponseDTO;
import com.hackathon.inditex.mappers.CenterMapper;
import com.hackathon.inditex.services.CenterService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/centers")
@RequiredArgsConstructor
public class CenterController {

    private final CenterService centerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDTO createNewCenter(@RequestBody CenterDTO newCenterDTO) {

        Center newCenter = CenterMapper.centerDTOtoCenter(newCenterDTO);

        return new ResponseDTO(centerService.saveCenter(newCenter));

    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Center> readCenters() {
        return centerService.readCenters();
    }

}
