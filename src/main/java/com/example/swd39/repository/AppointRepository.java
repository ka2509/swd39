package com.example.swd39.repository;

import com.example.swd39.model.Appointments;
import jakarta.persistence.Table;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointRepository extends JpaRepository<Appointments, Long> {

    @Query(value = "SELECT a.* \n"
    + "FROM \n"
    + "`appointments` a \n"
    + "WHERE a.patientId = :idUser", nativeQuery = true)
    List<Appointments> getPatientAppointment(Long idUser);

   @Transactional
    @Modifying
    @Query(value = "UPDATE `appointments` a \n"
           + "SET a.doctorId = :doctorId, a.serviceId = :serviceId, a.timeslotId = :timeId, a.note = :note \n"
           + "WHERE a.id = :idAppointment", nativeQuery = true)
    void updateAppointment(Long idAppointment, Long doctorId, Long serviceId, Long timeId, String note);

   @Query(value = "SELECT a.* FROM `appointments` a WHERE a.status = 'pending'", nativeQuery = true)
    List<Appointments> getPendingAppointment();

    @Transactional
    @Modifying
    @Query(value = "UPDATE `appointments` a SET a.status = 'rejected' WHERE a.id = :idAppointment",nativeQuery = true)
    void rejectAppointment(Long idAppointment);

    @Transactional
    @Modifying
    @Query(value = "UPDATE `appointments` a SET a.status = 'accepted' WHERE a.id = :idAppointment",nativeQuery = true)
    void acceptAppointment(Long idAppointment);

   @Query(value = "SELECT a.* FROM `appointments` a WHERE a.doctorId = :idDoctor and a.status = 'accepted'", nativeQuery = true)
    List<Appointments> getAcceptedAppointment(Long idDoctor);
}
