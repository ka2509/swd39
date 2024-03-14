package com.example.swd39.repository;

import com.example.swd39.model.TimeSlots;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TimeSlotsRepository extends JpaRepository<TimeSlots, Long> {

}
