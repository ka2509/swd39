package com.example.swd39.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class TimeSlots {
    private String startAt;
    private String endAt;

    @OneToMany(mappedBy = "timeSlot", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Appointments> appointment;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

}
