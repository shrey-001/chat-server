package com.example.demo.repositories;

import com.example.demo.models.dtos.ReactionDTO;
import com.example.demo.models.entities.Reaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ReactionRepo extends JpaRepository<Reaction, Long> {
    List<Reaction> getReactionsByMessageId(Long messageId);

    List<Reaction> getReactionsByMessageIds(List<Long> messageIds);
}
