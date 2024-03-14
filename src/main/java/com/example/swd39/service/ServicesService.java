package com.example.swd39.service;

import com.example.swd39.exception.ServicesException;
import com.example.swd39.model.Services;
import com.example.swd39.repository.ServicesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicesService {
    @Autowired
    private ServicesRepository servicesRepository;
    public Services findById(Long serviceId) throws ServicesException {
        return servicesRepository.findById(serviceId)
                .orElseThrow(() -> new SecurityException("dont have this service"));
    }

    public List<Services> getAllServices() {
        return servicesRepository.findAll();
    }
}
