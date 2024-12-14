package com.example.demo.controllers;


import com.example.demo.dispatcher.ReceiveDispatcher;
import com.example.demo.config.SessionRegistry;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
@Slf4j
@AllArgsConstructor
public class SocketHandler extends TextWebSocketHandler {

    private final SessionRegistry sessionRegistry;
    private final ReceiveDispatcher dispatcher;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {

        String userId = session.getHandshakeHeaders().get("userId").get(0);
        log.info("WebSocket connected: {}, UserId: {}", session.getId(), userId);
        sessionRegistry.addSession(userId, session);
    }
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        log.error("Message received: " + message.getPayload());
        dispatcher.dispatchReceive(session,message.getPayload());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, org.springframework.web.socket.CloseStatus status) throws Exception {
        String userId = session.getHandshakeHeaders().get("userId").get(0);
        sessionRegistry.removeSession(userId);
        log.error("WebSocket disconnected: {}" , session.getId());
    }

}
