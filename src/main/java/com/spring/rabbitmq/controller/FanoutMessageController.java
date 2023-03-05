package com.spring.rabbitmq.controller;

import com.spring.rabbitmq.model.MessageDto;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/fanout")
public class FanoutMessageController {

    @Autowired
    private AmqpTemplate fanoutQueue;

    private int id = 1;
    @GetMapping("/send")
    public String sendMessage() {
          MessageDto messageDto = new MessageDto(id++,"Fanout Message number ",LocalDateTime.now());

        fanoutQueue.convertAndSend(messageDto);
        return "FanOut Message to all ";
    }
}
