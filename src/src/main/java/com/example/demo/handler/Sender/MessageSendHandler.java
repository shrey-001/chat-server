package com.example.demo.handler.Sender;

import org.springframework.web.socket.WebSocketSession;

public interface MessageSendHandler<T> {
    void handleSend(WebSocketSession session, T payload);
    String getSupportedType();
}
