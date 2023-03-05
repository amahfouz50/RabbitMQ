package com.spring.rabbitmq.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Setter
@Getter
@AllArgsConstructor
public class MessageDto {

    private int id;
    private String message;
    private LocalDateTime localDateTime;

}
