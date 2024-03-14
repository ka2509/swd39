package com.example.swd39.controller;

import com.example.swd39.service.ServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/services")
public class ServicesController {
    @Autowired
    private ServicesService servicesService;
    @GetMapping("/getAllServices")
     ResponseEntity<?> getAllServices() {
        return ResponseEntity.ok(servicesService.getAllServices());
    }
}
