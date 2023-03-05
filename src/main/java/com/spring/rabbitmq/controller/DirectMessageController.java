package com.spring.rabbitmq.controller;

import com.spring.rabbitmq.model.MessageDto;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/direct")
public class DirectMessageController {

    @Autowired
    private AmqpTemplate directQueue;

    @Value("${rabbit.direct1.binding}")
    private String binding1;

    @Value("${rabbit.direct2.binding}")
    private String binding2;

    @Value("${rabbit.direct3.binding}")
    private String binding3;

    private int id = 1;
    @GetMapping("/send/{num}")
    public String sendMessage(@PathVariable Integer num) throws Exception {
          MessageDto messageDto = new MessageDto(id++ ,"Direct Message number "+ num ,LocalDateTime.now());
        String key;

        if(num == 1){
            key = binding1;
        } else if (num == 2){
            key = binding2;
        } else if(num == 3){
            key = binding3;
        } else {
            throw new Exception("You must Enter 1,2 or 3 only");
        }
        directQueue.convertAndSend(key, messageDto);
        return "Direct Message number "+ num;
    }
}
