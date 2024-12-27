package com.hackathon.inditex.Controllers;

import com.hackathon.inditex.Entities.Center;
import com.hackathon.inditex.Entities.Order;
import com.hackathon.inditex.dtos.CenterDTO;
import com.hackathon.inditex.dtos.ResponseDTO;
import com.hackathon.inditex.mappers.ICenterMapper;
import com.hackathon.inditex.services.ICenterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final IOrderService customOrderService;
    private final IOrderMapper orderMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseOrderDTO createNewOrder(@RequestBody OrderDTO newOrderDTO) {

        Order newOrder = orderMapper.orderDTOtoOrder(newOrderDTO);

        return new ResponseDTO(customOrderService.saveOrder(newOrder));

    }

}
