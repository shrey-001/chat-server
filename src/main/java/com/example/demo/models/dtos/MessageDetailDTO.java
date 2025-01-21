package com.example.demo.models.dtos;

import lombok.Data;

import java.util.List;

@Data
public class MessageDetailDTO extends MessageDTO{
    List<ReactionDTO> reactions;
}
