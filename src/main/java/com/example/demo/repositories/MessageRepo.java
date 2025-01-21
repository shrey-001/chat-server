package com.example.demo.repositories;

import com.example.demo.models.dtos.MessageDTO;
import com.example.demo.models.entities.Message;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepo extends JpaRepository<Message, Long> {

    List<Message> getMessagesByChatId(Long chatId, PageRequest pageRequest);
}
