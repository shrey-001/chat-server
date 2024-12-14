package com.example.demo.models.dtos;


import com.example.demo.models.ReceiveMessageType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MessageContainer<T> {
    ReceiveMessageType type;
    T payload;
}
