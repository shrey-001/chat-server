package com.example.demo.repositories;

import com.example.demo.models.entities.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChatRepo extends JpaRepository<Chat, Long> {

    Optional<Chat> findOneById(Long chatId);
}
