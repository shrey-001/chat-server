package com.example.demo.services.dbservices;

import com.example.demo.models.entities.Chat;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ChatService {

    Chat createChat(String chatName, List<Long> participantIds);

    void addParticipant(Long chatId, Long participantId);

    List<Long> getChatParticipants(Long chatId);
}
