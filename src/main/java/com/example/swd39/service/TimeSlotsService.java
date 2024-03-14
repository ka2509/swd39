package com.example.swd39.service;

import com.example.swd39.exception.TimeSlotsException;
import com.example.swd39.exception.UserException;
import com.example.swd39.model.TimeSlots;
import com.example.swd39.repository.TimeSlotsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TimeSlotsService {
    @Autowired
    private TimeSlotsRepository timeSlotsRepository;
    public TimeSlots findById(Long timeId) throws TimeSlotsException {
        return timeSlotsRepository.findById(timeId)
                .orElseThrow(() -> new TimeSlotsException("dont have this time slot"));
    }

    public List<TimeSlots> getAllTimeSlots() {
        return timeSlotsRepository.findAll();
    }
}
