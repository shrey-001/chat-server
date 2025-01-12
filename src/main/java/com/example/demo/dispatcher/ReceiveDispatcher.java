package com.example.demo.dispatcher;

import com.example.demo.handler.Receiver.ReceiveHandler;
import com.example.demo.models.ReceiveMessageType;
import com.example.demo.models.dtos.MessageContainer;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@AllArgsConstructor
public class ReceiveDispatcher {
    private final Map<ReceiveMessageType, ReceiveHandler<?>> receivers = new HashMap<>();
    private final ObjectMapper objectMapper= new ObjectMapper();

    @Autowired
    public ReceiveDispatcher(List<ReceiveHandler<?>> handlerList) {
        for (ReceiveHandler<?> handler : handlerList) {
            receivers.put(handler.getSupportedType(), handler);
        }
    }

    public void dispatchReceive(WebSocketSession session, String message) {
        try {

            MessageContainer<?> messageDTO = objectMapper.readValue(message, new TypeReference<MessageContainer<Object>>() {});
            ReceiveMessageType type = messageDTO.getType();

            ReceiveHandler<?> handler = receivers.get(type);
            if (handler != null) {
                Class<?> payloadType = getPayloadType(handler);


                Object payload = objectMapper.convertValue(messageDTO.getPayload(),payloadType);
                ((ReceiveHandler<Object>) handler).handleReceive(session, payload);
            } else {
                throw new IllegalArgumentException("No receiver found for type: " + type);
            }
        } catch (Exception e) {
            throw new RuntimeException("Exception occured while dispatching");
        }
    }
    private Class<?> getPayloadType(ReceiveHandler<?> handler) {
        try {
            // Get the generic type parameter of the MessageReceiverHandler interface
            ParameterizedType genericInterface = (ParameterizedType) handler.getClass()
                    .getGenericInterfaces()[0];
            return (Class<?>) genericInterface.getActualTypeArguments()[0];
        } catch (Exception e) {
            // Log and handle exceptions gracefully
            e.printStackTrace();
            return null;
        }
    }
}

