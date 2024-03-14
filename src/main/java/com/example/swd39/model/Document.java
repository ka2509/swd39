package com.example.swd39.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String documents;

    @ManyToOne
    @JoinColumn(name = "patientId", referencedColumnName = "id")
    private User user;
}
