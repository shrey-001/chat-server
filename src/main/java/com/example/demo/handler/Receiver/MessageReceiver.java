package com.example.demo.handler.Receiver;

import com.example.demo.models.ReceiveMessageType;
import com.example.demo.models.dtos.MessageDTO;
import com.example.demo.services.MessagingService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;


@Component
@AllArgsConstructor
public class MessageReceiver implements ReceiveHandler<MessageDTO> {
    private final MessagingService messagingService;

    @Override
    public void handleReceive(WebSocketSession session, MessageDTO payload) {
        messagingService.handleMessage(payload);
    }

    @Override
    public ReceiveMessageType getSupportedType() {
        return ReceiveMessageType.MESSAGE;
    }
}
