package com.example.demo.repositories;

import com.example.demo.models.entities.ChatParticipant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatParticipantRepo extends JpaRepository<ChatParticipant, Long>{

    @Query("SELECT cp.participantId FROM ChatParticipant cp WHERE cp.chatId = ?1")
    List<Long> getChatParticipants(Long chatId);
}
