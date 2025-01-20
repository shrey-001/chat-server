package com.example.demo.services.kafka;

import com.example.demo.dispatcher.SendDispatcher;
import com.example.demo.models.dtos.MessageContainer;
import com.example.demo.models.dtos.MessageDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class KafkaListenerService {

    private final ObjectMapper objectMapper;
    private final SendDispatcher sendDispatcher;

    @KafkaListener(topics = "topic_name", groupId = "group_id")
    public void consume(String message) {
        log.error(String.format("#### -> Consumed message -> %s", message));
        try{
            MessageContainer<MessageDTO> parsedMsg = objectMapper.readValue(message, new TypeReference<MessageContainer<MessageDTO>>() {});
            sendDispatcher.sendToUser(parsedMsg.getPayload().getReceiverId(), parsedMsg);
        }
        catch (Exception e){
            log.error("Error while consuming message, error is {}", e.getMessage());
        }
    }
}
