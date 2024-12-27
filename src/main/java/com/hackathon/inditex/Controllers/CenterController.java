package com.hackathon.inditex.Controllers;

import com.hackathon.inditex.Entities.Center;
import com.hackathon.inditex.dtos.CenterDTO;
import com.hackathon.inditex.dtos.ResponseDTO;
import com.hackathon.inditex.mappers.ICenterMapper;
import com.hackathon.inditex.services.ICenterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/centers")
@RequiredArgsConstructor
public class CenterController {

    private final ICenterService customCenterService;
    private final ICenterMapper centerMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDTO createNewCenter(@RequestBody CenterDTO newCenterDTO) {

        Center newCenter = centerMapper.centerDTOtoCenter(newCenterDTO);

        return new ResponseDTO(customCenterService.saveCenter(newCenter));

    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Center> readCenters() {
        return customCenterService.readCenters();
    }

    @PatchMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseDTO updateCenter(@PathVariable Long id, @RequestBody CenterDTO updatedCenterDTO) {

        return new ResponseDTO(customCenterService.updateCenter(id, updatedCenterDTO));

    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseDTO deleteCenterById(@PathVariable Long id) {
        return new ResponseDTO(customCenterService.deleteCenter(id));
    }

}
