package com.spring.rabbitmq.service;

import com.spring.rabbitmq.model.MessageDto;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class ConsumerService {

    @Autowired
    private AmqpAdmin amqpAdmin;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private int getMessageCount(String queueName) {
        Properties properties = amqpAdmin.getQueueProperties(queueName);
        return (int) properties.get(RabbitAdmin.QUEUE_MESSAGE_COUNT);
    }

    public List<MessageDto> getMessages(String queueName) {
        return IntStream.range(0, getMessageCount(queueName))
                .mapToObj(value -> (MessageDto) rabbitTemplate.receiveAndConvert(queueName))
                .collect(Collectors.toList());
    }

    public List<MessageDto> getMessagesByName(String queueName, String messageName) {
        return IntStream.range(0, getMessageCount(queueName))
                .mapToObj(value -> (MessageDto) rabbitTemplate.receiveAndConvert(queueName))
                .filter(message -> message.getMessage().contains(messageName)).collect(Collectors.toList());
    }

    public List<MessageDto> getMessagesById(String queueName, int id) {
        return IntStream.range(0, getMessageCount(queueName))
                .mapToObj(value -> (MessageDto) rabbitTemplate.receiveAndConvert(queueName))
                .filter(message -> message.getId() == id)
                .collect(Collectors.toList());
    }

}
