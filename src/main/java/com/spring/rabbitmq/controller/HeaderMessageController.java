package com.spring.rabbitmq.controller;

import com.spring.rabbitmq.model.MessageDto;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/header")
public class HeaderMessageController {

    @Autowired
    private AmqpTemplate headerQueue;
    private int id = 1;

    @GetMapping("/send")
    public String sendMessage(
            @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "debug", required = false) String debug
    ) {
        MessageDto messageDto = new MessageDto(id++,"Header Message  ", LocalDateTime.now());
        MessageBuilder messageBuilder = MessageBuilder.withBody(messageDto.toString().getBytes());
        if (error != null) {
            messageBuilder.setHeader("error", error);
        }
        if (debug != null) {
            messageBuilder.setHeader("debug", debug);
        }
        headerQueue.convertAndSend(messageBuilder.build());
        return "Header Message   ";
    }
}
