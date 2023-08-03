package com.nominori.oms.application.order.events;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderCreatedEventPublisher {

    private final RabbitTemplate rabbitTemplate;

    @Bean
    public Queue createOrderCreatedQueue(){
        return new Queue("q.order.created");
    }

    public void publish(OrderCreatedEvent event){
        rabbitTemplate.convertAndSend("q.order.created", event);
    }

}
