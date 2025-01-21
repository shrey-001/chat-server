package com.example.demo.services.dbservices;

import com.example.demo.models.dtos.ReactionDTO;

import java.util.List;
import java.util.Map;

public interface ReactionService {
    void saveReaction(String reaction, Long messageId, Long userId);

    List<ReactionDTO> getReactionsByMessageId(Long messageId);

    Map<Long, List<ReactionDTO>> getReactionsByMessageIds(List<Long> messageIds);
}
