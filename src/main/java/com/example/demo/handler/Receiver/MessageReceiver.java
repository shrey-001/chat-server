package com.example.demo.handler.Receiver;

import com.example.demo.models.ReceiveMessageType;
import com.example.demo.models.dtos.MessageContainer;
import com.example.demo.models.dtos.MessageDTO;
import com.example.demo.services.kafka.KafkaProducerService;
import com.example.demo.services.servicesImpl.MessagingServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;


@Component
@AllArgsConstructor
public class MessageReceiver implements ReceiveHandler<MessageDTO> {

    private final KafkaProducerService kafkaProducerService;

    @Override
    public void handleReceive(WebSocketSession session, MessageDTO payload) {
        MessageContainer<MessageDTO> messageContainer = new MessageContainer<>();
        messageContainer.setType(ReceiveMessageType.MESSAGE);
        messageContainer.setPayload(payload);

        kafkaProducerService.sendDemoProducer(messageContainer);
    }

    @Override
    public ReceiveMessageType getSupportedType() {
        return ReceiveMessageType.MESSAGE;
    }
}
