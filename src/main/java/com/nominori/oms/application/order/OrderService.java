package com.nominori.oms.application.order;

import com.nominori.oms.core.order.Order;

public interface OrderService {
    Order createOrder(Order order);
    void removeOrder(Long id);

}
