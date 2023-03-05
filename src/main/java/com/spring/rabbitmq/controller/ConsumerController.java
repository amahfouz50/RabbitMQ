package com.spring.rabbitmq.controller;

import com.spring.rabbitmq.model.MessageDto;
import com.spring.rabbitmq.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ConsumerController {

    @Autowired
    private ConsumerService consumerService;


    @GetMapping("/messages/{queueName}")
    public List<MessageDto> receiveMessages(@PathVariable String queueName){
        return consumerService.getMessages(queueName);
    }
    @GetMapping("/messages/{queueName}/{messageName}")
    public List<MessageDto> receiveMessagesByName(@PathVariable String queueName , @PathVariable String messageName){
        return consumerService.getMessagesByName(queueName,messageName);
    }
    @GetMapping("/message/{queueName}/{id}")
    public List<MessageDto> receiveMessagesById(@PathVariable String queueName , @PathVariable int id){
        return consumerService.getMessagesById(queueName,id);
    }


}
