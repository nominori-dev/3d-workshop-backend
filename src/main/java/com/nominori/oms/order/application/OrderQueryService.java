package com.nominori.oms.order.application;

import com.nominori.oms.order.domain.Order;

public interface OrderQueryService {
    Order findById(Long id);
}
