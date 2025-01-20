package com.example.demo.dispatcher;


import com.example.demo.config.SessionRegistry;
import com.example.demo.models.ReceiveMessageType;
import com.example.demo.models.dtos.MessageContainer;
import com.example.demo.models.dtos.MessageDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

@Component
@Slf4j
@AllArgsConstructor
public class SendDispatcher {

    private final SessionRegistry sessionRegistry;

    private final ObjectMapper objectMapper = new ObjectMapper();


    public <T> void sendToUser(String userId, T message) {
        WebSocketSession session = sessionRegistry.getSession(userId);
        if (Objects.isNull(session) || !session.isOpen()) {
            log.error("Failed to send message. Session for userId: {} is not open or does not exist", userId);
            return;
        }
        try {
            String messageString = objectMapper.writeValueAsString(message);
            session.sendMessage(new TextMessage(messageString));
            log.error("Message sent to userId: {}: {}", userId, message);
        } catch (IOException e) {
            log.error("Error sending message to userId: {}", userId, e);
        }
    }
}

