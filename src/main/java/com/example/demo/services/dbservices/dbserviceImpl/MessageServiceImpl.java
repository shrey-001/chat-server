package com.example.demo.services.dbservices.dbserviceImpl;


import com.example.demo.models.dtos.MessageDTO;
import com.example.demo.models.dtos.MessageDetailDTO;
import com.example.demo.models.entities.Message;
import com.example.demo.repositories.MessageRepo;
import com.example.demo.services.dbservices.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageRepo messageRepo;

    @Override
    public void saveMessage(MessageDTO messageDTO){
        Message msg = new Message();

        msg.setChatId(messageDTO.getChatId());
        msg.setSenderId(messageDTO.getSenderId());
        msg.setContent(messageDTO.getContent());

        messageRepo.save(msg);
    }

    @Override
    public List<MessageDetailDTO> getMessagesByChatId(Long chatId, Long page, Long size) {

        //Pageable with sort by created_at latest first
        PageRequest pageRequest = PageRequest.of(page.intValue(), size.intValue(), Sort.by("created_at").descending());

        List<Message> messages = messageRepo.getMessagesByChatId(chatId, pageRequest);

        return messages.stream().map(message -> {
            MessageDetailDTO messageDTO = new MessageDetailDTO();
            BeanUtils.copyProperties(message, messageDTO);
            return messageDTO;
        }).toList();
    }
}
