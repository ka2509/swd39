package com.example.swd39.service;

import com.example.swd39.exception.AppointmentException;
import com.example.swd39.exception.ServicesException;
import com.example.swd39.exception.TimeSlotsException;
import com.example.swd39.exception.UserException;
import com.example.swd39.model.Appointments;
import com.example.swd39.model.Status;
import com.example.swd39.model.User;
import com.example.swd39.repository.AppointRepository;
import com.example.swd39.request.AppointmentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AppointService {
    @Autowired
    private AppointRepository appointRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private TimeSlotsService timeSlotsService;
    @Autowired
    private ServicesService servicesService;

    public Appointments createAppointment(String email, AppointmentRequest request) throws UserException, TimeSlotsException, ServicesException {
        Appointments newAppointment = new Appointments();
        newAppointment.setPatient(userService.findUserByEmail(email));
        newAppointment.setDoctor(userService.findUserById(request.getDoctorId()));
        newAppointment.setCreatedAt(LocalDateTime.now());
        newAppointment.setStatus(Status.pending);
        newAppointment.setNote(request.getNote());
        newAppointment.setTimeSlot(timeSlotsService.findById(request.getTimeId()));
        newAppointment.setSelectedService(servicesService.findById(request.getServiceId()));
        return appointRepository.save(newAppointment);
    }
    public Appointments findById(Long idAppointment) throws AppointmentException {
        return appointRepository.findById(idAppointment)
                .orElseThrow(() -> new AppointmentException("Can't find this appointment"));
    }
    public List<Appointments> getPatientAppointment(Long idUser) {

        return appointRepository.getPatientAppointment(idUser);
    }

    public void deleteAppointment(Long idAppointment) {
        appointRepository.deleteById(idAppointment);
    }

    public Appointments updateAppointment(Long idAppointment, AppointmentRequest request) throws AppointmentException {
         appointRepository.updateAppointment(idAppointment, request.getDoctorId(), request.getServiceId(), request.getTimeId(), request.getNote());
         return findById(idAppointment);
    }

    public List<Appointments> viewPendingAppointment() {
        return appointRepository.getPendingAppointment();
    }

    public Appointments rejectAppointment(Long idAppointment) throws AppointmentException {
        appointRepository.rejectAppointment(idAppointment);
        return findById(idAppointment);
    }

    public Appointments acceptAppointment(Long idAppointment) throws AppointmentException {
        appointRepository.acceptAppointment(idAppointment);
        return findById(idAppointment);
    }

    public List<Appointments> viewAcceptedAppointment(Long idDoctor) {
        return appointRepository.getAcceptedAppointment(idDoctor);
    }
}
