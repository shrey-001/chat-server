package com.example.demo.services.servicesImpl;

import com.example.demo.dispatcher.SendDispatcher;
import com.example.demo.models.ReceiveMessageType;
import com.example.demo.models.dtos.MessageContainer;
import com.example.demo.models.dtos.MessageDTO;
import com.example.demo.models.dtos.MessageDetailDTO;
import com.example.demo.models.dtos.ReactionDTO;
import com.example.demo.models.entities.Chat;
import com.example.demo.services.MessagingService;
import com.example.demo.services.dbservices.ChatService;
import com.example.demo.services.dbservices.MessageService;
import com.example.demo.services.dbservices.ReactionService;
import com.example.demo.services.kafka.KafkaProducerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@Slf4j
@AllArgsConstructor
public class MessagingServiceImpl implements MessagingService {

    private final SendDispatcher sendDispatcher;
    private final KafkaProducerService kafkaProducerService;

    private final ChatService chatService;
    private final MessageService messageService;
    private final ReactionService reactionService;


    @Override
    public List<MessageDetailDTO> getMessagesByChatId(Long chatId, Long page, Long size) {

        List<MessageDetailDTO> messages = messageService.getMessagesByChatId(chatId, page, size);
        //fetch Reaction for each message and set to reactions
        Map<Long, List<ReactionDTO>> reactions = reactionService.getReactionsByMessageIds(messages.stream().map(MessageDetailDTO::getId).toList());
        messages.forEach(message -> {
            message.setReactions(reactions.get(message.getId()));
        });
        return messages;
    }

    @Override
    public Chat createChat(String chatName, List<Long> participantIds) {
        return chatService.createChat(chatName, participantIds);
    }

    @Override
    public void reactToMessage(Long messageId, String type, Long userId) {
        reactionService.saveReaction(type, messageId, userId);
    }


}
