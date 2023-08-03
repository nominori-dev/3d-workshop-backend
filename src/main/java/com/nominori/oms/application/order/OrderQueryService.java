package com.nominori.oms.application.order;

import com.nominori.oms.core.order.Order;

public interface OrderQueryService {
    Order findById(Long id);
}
