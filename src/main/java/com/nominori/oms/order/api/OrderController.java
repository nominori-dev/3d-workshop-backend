package com.nominori.oms.order.api;

import com.nominori.oms.order.api.converter.CreateOrderConverter;
import com.nominori.oms.order.api.dto.CreateOrderDto;
import com.nominori.oms.order.application.OrderService;
import com.nominori.oms.order.domain.Order;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order/")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final CreateOrderConverter createOrderConverter;

    @PostMapping
    public Order createOrder(@RequestBody @Valid CreateOrderDto dto){
        return orderService
                .createOrder(createOrderConverter.toEntity(dto));
    }
    
}
