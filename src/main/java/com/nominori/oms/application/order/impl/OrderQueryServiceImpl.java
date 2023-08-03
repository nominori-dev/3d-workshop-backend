package com.nominori.oms.application.order.impl;

import com.nominori.oms.api.exception.ResourceNotFoundException;
import com.nominori.oms.application.order.OrderQueryService;
import com.nominori.oms.core.order.Order;
import com.nominori.oms.core.order.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderQueryServiceImpl implements OrderQueryService {

    private final OrderRepository orderRepository;

    @Override
    public Order findById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order with provided ID not found."));
    }
}
