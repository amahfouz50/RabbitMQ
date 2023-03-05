package com.spring.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jakarta.annotation.PostConstruct;

@Configuration
public class TopicExchangeConfig {

    @Autowired
    private AmqpAdmin amqpAdmin;

    @Value("${rabbit.topic1.queue}")
    private String topicQueue1;

    @Value("${rabbit.topic2.queue}")
    private String topicQueue2;

    @Value("${rabbit.topic3.queue}")
    private String topicQueue3;

    @Value("${rabbit.deed-line-queue}")
    private String topicQueue4;

    @Value("${rabbit.topic1.binding}")
    private String binding1;

    @Value("${rabbit.topic2.binding}")
    private String binding2;

    @Value("${rabbit.topic3.binding}")
    private String binding3;

    @Value("${rabbit.topic.exchange}")
    private String exchange;


    @Bean
    Queue createTopicQueue1() {
        return new Queue(topicQueue1, true, false, false);
    }
    @Bean
    Queue createTopicQueue2() {
        return new Queue(topicQueue2, true, false, false);
    }
    @Bean
    Queue createTopicQueue3() {
        return new Queue(topicQueue3, true, false, false);
    }

    @Bean
    TopicExchange createTopicExchange() {
        return new TopicExchange(exchange);
    }

    @Bean
    Binding createTopicBinding1(){
        return BindingBuilder.bind(createTopicQueue1()).to(createTopicExchange()).with(binding1);
    }
    @Bean
    Binding createTopicBinding2(){
        return BindingBuilder.bind(createTopicQueue2()).to(createTopicExchange()).with(binding2);
    }
    @Bean
    Binding createTopicBinding3(){
        return BindingBuilder.bind(createTopicQueue3()).to(createTopicExchange()).with(binding3);
    }

    @Bean
    public AmqpTemplate topicQueue(ConnectionFactory connectionFactory, MessageConverter messageConverter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter);
        rabbitTemplate.setExchange(exchange);
        return rabbitTemplate;

    }
    @PostConstruct
    public void init(){
        amqpAdmin.declareQueue(createTopicQueue1());
        amqpAdmin.declareQueue(createTopicQueue2());
        amqpAdmin.declareQueue(createTopicQueue3());
        amqpAdmin.declareExchange(createTopicExchange());
        amqpAdmin.declareBinding(createTopicBinding1());
        amqpAdmin.declareBinding(createTopicBinding2());
        amqpAdmin.declareBinding(createTopicBinding3());
    }

}
