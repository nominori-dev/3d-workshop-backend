package com.nominori.oms.order.application;

import com.nominori.oms.order.domain.Order;

public interface OrderService {
    Order createOrder(Order order);
    void removeOrder(Long id);

}
