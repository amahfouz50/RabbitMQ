package com.spring.rabbitmq.controller;

import com.spring.rabbitmq.model.MessageDto;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/topic")
public class TopicMessageController {

    @Autowired
    private AmqpTemplate topicQueue;
    private int id = 1;
    @GetMapping("/send/{key}")
    public String sendMessage(@PathVariable String key)  {
          MessageDto messageDto = new MessageDto(id++,"Topic Message pattern " ,LocalDateTime.now());

        topicQueue.convertAndSend(key, messageDto);
        return "Topic Message pattern " ;
    }
}
