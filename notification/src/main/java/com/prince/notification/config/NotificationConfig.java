package com.prince.notification.config;


import lombok.Getter;
import lombok.Setter;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
public class NotificationConfig {

    @Bean
    public TopicExchange internalTopicExchange(){
        return new TopicExchange(this.internalExchange);
    }


    @Bean
    public Queue notificationQueue(){
        return new Queue(this.notificationQueue);
    }

    public Binding internalToNotificationBinding(){
        return BindingBuilder.bind(notificationQueue()).to(internalTopicExchange())
                .with(this.internalNotificationRoutingKey);
    }

    @Value("{rabbitmq.exchanges.internal}")
    private String internalExchange;

    @Value("{rabbitmq.exchanges.notification}")
    private String notificationQueue;

    @Value("{rabbitmq.routing-keys.internal-notification}")
    private String internalNotificationRoutingKey;

}
