package com.hackathon.inditex.Controllers;

import com.hackathon.inditex.Entities.Order;
import com.hackathon.inditex.dtos.OrderDTO;
import com.hackathon.inditex.dtos.ProcessedOrderDTO;
import com.hackathon.inditex.dtos.ResponseOrderAssignationsDTO;
import com.hackathon.inditex.dtos.ResponseOrderDTO;
import com.hackathon.inditex.mappers.IOrderMapper;
import com.hackathon.inditex.services.IOrderAssignationsService;
import com.hackathon.inditex.services.IOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    public static final String ORDER_CREATED_SUCCESSFULLY = "Order created successfully in PENDING status.";

    private final IOrderService orderService;
    private final IOrderMapper orderMapper;
    private final IOrderAssignationsService orderAssignationsService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseOrderDTO createNewOrder(@RequestBody OrderDTO newOrderDTO) {

        Order newOrder = orderMapper.orderDTOtoOrder(newOrderDTO);
        Order newOrderFinal = orderService.saveOrder(newOrder);

        return new ResponseOrderDTO(
                newOrderFinal.getId(),
                newOrderFinal.getCustomerId(),
                newOrderFinal.getSize(),
                newOrderFinal.getAssignedCenter(),
                newOrderFinal.getCoordinates(),
                newOrderFinal.getStatus(),
                ORDER_CREATED_SUCCESSFULLY);

    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Order> readOrders() {
        return orderService.readOrders();
    }

    @PostMapping("order-assignations")
    @ResponseStatus(HttpStatus.OK)
    public ResponseOrderAssignationsDTO orderAssignations() {

        List<ProcessedOrderDTO> listProcessedOrdersDTO = orderAssignationsService.assignCenterToOrders();

        return new ResponseOrderAssignationsDTO(listProcessedOrdersDTO);
    }
}
