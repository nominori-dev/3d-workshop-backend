package com.nominori.oms.order.application.impl;

import com.nominori.oms.shared.api.exception.ResourceNotFoundException;
import com.nominori.oms.order.application.OrderQueryService;
import com.nominori.oms.order.domain.Order;
import com.nominori.oms.order.repository.OrderRepository;
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
