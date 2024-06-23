package com.nominori.oms.order.application.impl;

import com.nominori.oms.order.application.OrderQueryService;
import com.nominori.oms.order.application.OrderService;
import com.nominori.oms.order.application.events.OrderCreatedEvent;
import com.nominori.oms.order.application.events.OrderCreatedEventPublisher;
import com.nominori.oms.order.domain.Order;
import com.nominori.oms.order.repository.OrderItemRepository;
import com.nominori.oms.order.repository.OrderRepository;
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
