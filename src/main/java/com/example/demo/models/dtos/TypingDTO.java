package com.example.demo.models.dtos;

import lombok.Data;

@Data
public class TypingDTO {
    private Long chatId;
    private Long senderId;
    private Boolean typing;
}
