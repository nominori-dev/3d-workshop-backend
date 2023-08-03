package com.nominori.oms.application.order.impl;

import com.nominori.oms.application.order.OrderQueryService;
import com.nominori.oms.application.order.OrderService;
import com.nominori.oms.application.order.events.OrderCreatedEvent;
import com.nominori.oms.application.order.events.OrderCreatedEventPublisher;
import com.nominori.oms.core.order.Order;
import com.nominori.oms.core.order.OrderItemRepository;
import com.nominori.oms.core.order.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final OrderQueryService orderQueryService;
    private final OrderCreatedEventPublisher publisher;

    @Override
    public Order createOrder(Order order) {
        orderRepository.save(order);
        publisher.publish(new OrderCreatedEvent(order));
        return order;
    }

    @Override
    public void removeOrder(Long id) {
        Order order = orderQueryService.findById(id);
        orderRepository.delete(order);
    }
}
