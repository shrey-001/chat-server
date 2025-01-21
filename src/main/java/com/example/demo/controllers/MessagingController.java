package com.example.demo.controllers;

import com.example.demo.models.dtos.MessageDetailDTO;
import com.example.demo.models.entities.Chat;
import com.example.demo.services.MessagingService;
import com.example.demo.services.servicesImpl.MessagingServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@AllArgsConstructor
public class MessagingController {
    private final MessagingService messagingService;

    @PostMapping("/messages/{chatId}")
    public List<MessageDetailDTO> getMessagesByChatId(@PathVariable Long chatId, @RequestParam Long page, @RequestParam Long size) {
        return messagingService.getMessagesByChatId(chatId, page, size);
    }

    @PostMapping("/chat/create")
    public Chat createChat(@RequestParam String chatName, @RequestParam List<Long> participantIds) {
        return messagingService.createChat(chatName, participantIds);
    }

    @PostMapping("/message/react/{messageId}")
    public void reactToMessage(@PathVariable Long messageId, @RequestParam String type, @RequestParam Long userId) {
        messagingService.reactToMessage(messageId, type, userId);
    }
}
