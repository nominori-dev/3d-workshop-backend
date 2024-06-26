package com.nominori.oms.order.application.listener;

import com.nominori.oms.order.application.events.OrderCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderCreatedListener {

    @RabbitListener(queues = "q.order.created")
    public void onOrderCreated(OrderCreatedEvent event){
        log.info("New order created with ID: " + event.getOrder().getId());
    }

}
