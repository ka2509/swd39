package com.example.swd39.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
public class Appointments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime arragedDate;

    @Enumerated(EnumType.STRING)
    private Status status;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String note;


    @ManyToOne
    @JoinColumn(name = "doctorId", referencedColumnName = "id")
    @JsonIgnore
    private User doctor;

    @ManyToOne
    @JoinColumn(name = "patientId", referencedColumnName = "id")
    @JsonIgnore
    private User patient;

    @ManyToOne
    @JoinColumn(name = "arragedBy", referencedColumnName = "id")
    @JsonIgnore
    private User arragedBy;

    @ManyToOne
    @JoinColumn(name = "timeslotId", referencedColumnName = "id")
    private TimeSlots timeSlot;

    @ManyToOne
    @JoinColumn(name = "serviceId", referencedColumnName = "id")
    private Services selectedService;

}
