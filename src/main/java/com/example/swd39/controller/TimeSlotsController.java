package com.example.swd39.controller;

import com.example.swd39.service.TimeSlotsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/timeSlots")
public class TimeSlotsController {
    @Autowired
    private TimeSlotsService timeSlotsService;
    @GetMapping
    ResponseEntity<?> getAllTimeSlots() {
        return ResponseEntity.ok(timeSlotsService.getAllTimeSlots());
    }
}
