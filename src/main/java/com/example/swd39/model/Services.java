package com.example.swd39.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Services {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String serviceTitle;
    private String serviceDetails;

    @OneToMany(mappedBy = "selectedService")
    @JsonIgnore
    private List<Appointments> appointments;
}
