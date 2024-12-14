package com.example.demo.handler.Receiver;

import com.example.demo.models.ReceiveMessageType;
import org.springframework.web.socket.WebSocketSession;

public interface ReceiveHandler<T> {
    void handleReceive(WebSocketSession session, T payload);
    ReceiveMessageType getSupportedType();
}
