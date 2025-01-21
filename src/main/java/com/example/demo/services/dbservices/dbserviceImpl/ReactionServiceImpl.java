package com.example.demo.services.dbservices.dbserviceImpl;

import com.example.demo.models.dtos.ReactionDTO;
import com.example.demo.models.entities.Reaction;
import com.example.demo.repositories.ReactionRepo;
import com.example.demo.services.dbservices.ReactionService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ReactionServiceImpl implements ReactionService {

    private final ReactionRepo reactionRepo;

    @Override
    public void saveReaction(String type, Long messageId, Long userId) {
        Reaction reaction = new Reaction();
        reaction.setType(type);
        reaction.setMessageId(messageId);
        reaction.setReactedBy(userId);
        reactionRepo.save(reaction);
    }

    @Override
    public final List<ReactionDTO> getReactionsByMessageId(Long messageId) {
        List<Reaction> reactions = reactionRepo.getReactionsByMessageId(messageId);

        return reactions.stream().map(reaction -> {
            ReactionDTO reactionDTO = new ReactionDTO();
            BeanUtils.copyProperties(reaction, reactionDTO);
            return reactionDTO;
        }).toList();


    }

    @Override
    public Map<Long, List<ReactionDTO>> getReactionsByMessageIds(List<Long> messageIds) {
        List<Reaction> reactions = reactionRepo.getReactionsByMessageIds(messageIds);

        return reactions.stream().collect(Collectors.groupingBy(Reaction::getMessageId, Collectors.mapping(reaction -> {
            ReactionDTO reactionDTO = new ReactionDTO();
            BeanUtils.copyProperties(reaction, reactionDTO);
            return reactionDTO;
        }, Collectors.toList())));
    }
}
