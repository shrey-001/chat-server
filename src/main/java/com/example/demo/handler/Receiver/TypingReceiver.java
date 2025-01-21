package com.example.demo.handler.Receiver;

import com.example.demo.models.ReceiveMessageType;
import com.example.demo.models.dtos.MessageContainer;
import com.example.demo.models.dtos.MessageDTO;
import com.example.demo.models.dtos.TypingDTO;
import com.example.demo.services.kafka.KafkaProducerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;


@Component
@AllArgsConstructor
public class TypingReceiver implements ReceiveHandler<TypingDTO> {

    private final KafkaProducerService kafkaProducerService;
    @Override
    public void handleReceive(WebSocketSession session, TypingDTO payload) {
        MessageContainer<TypingDTO> messageContainer = new MessageContainer<>();
        messageContainer.setType(ReceiveMessageType.TYPING);
        messageContainer.setPayload(payload);

        kafkaProducerService.sendTypingTopic(messageContainer);
    }

    @Override
    public ReceiveMessageType getSupportedType() {
        return ReceiveMessageType.TYPING;
    }
}
