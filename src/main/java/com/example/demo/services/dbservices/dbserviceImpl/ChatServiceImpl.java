package com.example.demo.services.dbservices.dbserviceImpl;

import com.example.demo.models.entities.Chat;
import com.example.demo.models.entities.ChatParticipant;
import com.example.demo.repositories.ChatParticipantRepo;
import com.example.demo.repositories.ChatRepo;
import com.example.demo.services.dbservices.ChatService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ChatServiceImpl implements ChatService {

    private final ChatRepo chatRepo;
    private final ChatParticipantRepo chatParticipantRepo;

    @Transactional
    @Override
    public Chat createChat(String chatName, List<Long> participantIds){
        // Create chat
        Chat chat = new Chat();
        chat.setChatName(chatName);
        chat = chatRepo.save(chat);

        List<ChatParticipant> chatParticipants = new ArrayList<>();
        // Add participants
        for (Long participantId : participantIds) {
            ChatParticipant chatParticipant = new ChatParticipant();
            chatParticipant.setChatId(chat.getId());
            chatParticipant.setParticipantId(participantId);

            chatParticipants.add(chatParticipant);
        }
        chatParticipantRepo.saveAll(chatParticipants);

        return chat;
    }
    @Transactional
    @Override
    public void addParticipant(Long chatId, Long participantId){

        Optional<Chat> chatOptional= chatRepo.findOneById(chatId);
        if(chatOptional.isEmpty()){
            throw new RuntimeException("Chat not found with chatId: "+ chatId);
        }

        ChatParticipant chatParticipant = new ChatParticipant();
        chatParticipant.setChatId(chatId);
        chatParticipant.setParticipantId(participantId);

        chatParticipantRepo.save(chatParticipant);
    }

    @Override
    public List<Long> getChatParticipants(Long chatId) {
        return chatParticipantRepo.getChatParticipants(chatId);
    }


}
