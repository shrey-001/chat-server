package com.example.demo.services.kafka;

import com.example.demo.dispatcher.SendDispatcher;
import com.example.demo.models.dtos.MessageContainer;
import com.example.demo.models.dtos.MessageDTO;
import com.example.demo.models.dtos.TypingDTO;
import com.example.demo.services.dbservices.ChatService;
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

    private final ChatService chatService;

    @KafkaListener(topics = "topic_name", groupId = "group_id")
    public void consume(String message) {
        log.error(String.format("#### -> Consumed message -> %s", message));
        try{
            MessageContainer<MessageDTO> parsedMsg = objectMapper.readValue(message, new TypeReference<MessageContainer<MessageDTO>>() {});

            MessageDTO messageDTO = parsedMsg.getPayload();
            //filter out the sender
            chatService.getChatParticipants(messageDTO.getChatId()).stream()
                    .filter(participantId -> !participantId.equals(messageDTO.getSenderId()))
                    .forEach(participantId -> sendDispatcher.sendToUser(participantId.toString(), message));
        }
        catch (Exception e){
            log.error("Error while consuming message, error is {}", e.getMessage());
        }
    }
    @KafkaListener(topics = "typing_topic", groupId = "group_id")
    public void consumeTypingTopic(String message) {
        log.error(String.format("#### -> Consumed message -> %s", message));
        try{
            MessageContainer<TypingDTO> parsedMsg = objectMapper.readValue(message, new TypeReference<MessageContainer<TypingDTO>>() {});

            TypingDTO typingDTO = parsedMsg.getPayload();
            //filter out the sender
            chatService.getChatParticipants(typingDTO.getChatId()).stream()
                    .filter(participantId -> !participantId.equals(typingDTO.getSenderId()))
                    .forEach(participantId -> sendDispatcher.sendToUser(participantId.toString(), message));
        }
        catch (Exception e){
            log.error("Error while consuming message, error is {}", e.getMessage());
        }
    }
}
