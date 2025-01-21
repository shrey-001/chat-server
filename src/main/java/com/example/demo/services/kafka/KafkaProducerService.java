package com.example.demo.services.kafka;

import com.example.demo.models.dtos.MessageContainer;
import com.example.demo.models.dtos.MessageDTO;
import com.example.demo.models.dtos.TypingDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.KafkaException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaProducerService {

    @Value("${kafka.topic-name.topic_name}")
    private String topicName;

    @Value("${kafka.topic-name.typing_topic}")
    private String typingTopic;

    private final KafkaTemplate<String, String> kafkaTemplate;

    private final ObjectMapper objectMapper;

    public void sendDemoProducer(MessageContainer<MessageDTO> message) {

        try {
            String msg = objectMapper.writeValueAsString(message);
            kafkaTemplate.send(topicName, msg);
        } catch (Exception e) {
            throw new KafkaException(String.format("Error while pushing message, error is %s", e.getMessage()));
        }
    }

    public void sendTypingTopic(MessageContainer<TypingDTO> typingContainer) {
        try {
            String msg = objectMapper.writeValueAsString(typingContainer);
            kafkaTemplate.send(topicName, msg);
        } catch (Exception e) {
            throw new KafkaException(String.format("Error while pushing message, error is %s", e.getMessage()));
        }
    }
}
