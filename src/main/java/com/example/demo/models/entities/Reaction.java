package com.example.demo.models.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Reaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;

    private Long messageId;

    private Long reactedBy;

    @Column(nullable = false, updatable = false)
    private Date reactedAt;
}
