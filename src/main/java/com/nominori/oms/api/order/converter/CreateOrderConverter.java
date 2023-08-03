package com.nominori.oms.api.order.converter;

import com.nominori.oms.api.order.dto.CreateOrderDto;
import com.nominori.oms.application.product.ProductQueryService;
import com.nominori.oms.core.order.Order;
import com.nominori.oms.core.order.OrderItem;
import com.nominori.oms.core.product.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
@Slf4j
public class CreateOrderConverter {

    private final ProductQueryService productQueryService;

    public Order toEntity(CreateOrderDto dto){
        Order order = new Order(dto.getEmail());
        dto.getOrderItems().forEach(orderItemDto -> {
            Product product = productQueryService.findById(orderItemDto.getProductId());
            Long quantity = orderItemDto.getQuantity();

            order.addOrderItem(new OrderItem(product, quantity));
        });

        order.calculateTotalPrice();
        return order;
    }

}
