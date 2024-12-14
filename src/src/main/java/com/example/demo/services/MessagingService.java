package com.example.demo.services;

import com.example.demo.dispatcher.SendDispatcher;
import com.example.demo.models.ReceiveMessageType;
import com.example.demo.models.dtos.MessageContainer;
import com.example.demo.models.dtos.MessageDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class MessagingService {

    private final SendDispatcher sendDispatcher;
    public void handleMessage(MessageDTO messageDTO){
        MessageContainer<MessageDTO> messageContainer = new MessageContainer<>();
        messageContainer.setType(ReceiveMessageType.MESSAGE);
        messageContainer.setPayload(messageDTO);


        sendDispatcher.sendToUser(messageDTO.getReceiverId(), messageContainer);
    }
}
