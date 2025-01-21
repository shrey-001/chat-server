package com.example.demo.models.dtos;


import lombok.Data;
import java.util.Date;


@Data
public class ReactionDTO {
    private Long id;
    private String type;
    private Long messageId;
    private Long reactedBy;
    private Date reactedAt;
}
