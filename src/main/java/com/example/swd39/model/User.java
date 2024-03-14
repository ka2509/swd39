package com.example.swd39.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Document> documents;

    @OneToMany(mappedBy = "doctor")
    private List<Appointments> appointments;

    @OneToMany(mappedBy = "patient")
    private List<Appointments> req_appointments;

    @OneToMany(mappedBy = "arragedBy")
    private List<Appointments> arragedAppointments;
}