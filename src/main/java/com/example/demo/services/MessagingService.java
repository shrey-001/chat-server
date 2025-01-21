package com.example.demo.services;

import com.example.demo.models.dtos.MessageDTO;
import com.example.demo.models.dtos.MessageDetailDTO;
import com.example.demo.models.entities.Chat;

import java.util.List;

public interface MessagingService {

    List<MessageDetailDTO> getMessagesByChatId(Long chatId, Long page, Long size);

    Chat createChat(String chatName, List<Long> participantIds);

    void reactToMessage(Long messageId, String type, Long userId);


}
