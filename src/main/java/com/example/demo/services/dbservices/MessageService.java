package com.example.demo.services.dbservices;

import com.example.demo.models.dtos.MessageDTO;
import com.example.demo.models.dtos.MessageDetailDTO;

import java.util.List;

public interface MessageService {

    void saveMessage(MessageDTO messageDTO);

    List<MessageDetailDTO> getMessagesByChatId(Long chatId, Long page, Long size);
}
