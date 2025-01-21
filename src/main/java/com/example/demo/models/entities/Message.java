package com.example.demo.models.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;


@Entity
@Data
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    private Long senderId;

    private Long chatId;

    @Column(nullable = false, updatable = false)
    private Date createdAt;

    @Column(nullable = false, updatable = false)
    private Date updatedAt;


}
