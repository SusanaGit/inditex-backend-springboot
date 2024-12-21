package com.hackathon.inditex.Controllers;
import com.hackathon.inditex.Entities.Center;
import com.hackathon.inditex.services.CenterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/centers")
@RequiredArgsConstructor
public class CenterController {

    private final CenterService centerService;

    @GetMapping
    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    public Center testCenter() {
        return centerService.testCenterService();
    }

}
