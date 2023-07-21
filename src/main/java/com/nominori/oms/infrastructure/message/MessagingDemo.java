package com.nominori.oms.infrastructure.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("dev")
@Component
@Slf4j
public class MessagingDemo {

    private final RabbitTemplate rabbitTemplate;

    public MessagingDemo(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        this.rabbitTemplate.convertAndSend("q.demo", new DemoMessage("Demo Message Object"));
    }

    @RabbitListener(queues = "q.demo")
    public void onDemo(DemoMessage event){
        log.info("Demo request received: {}", event.getContent());
    }

}
