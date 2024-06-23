package com.nominori.oms.order.api.converter;

import com.nominori.oms.order.api.dto.CreateOrderDto;
import com.nominori.oms.product.application.ProductQueryService;
import com.nominori.oms.order.domain.Order;
import com.nominori.oms.order.domain.OrderItem;
import com.nominori.oms.product.domain.Product;
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
