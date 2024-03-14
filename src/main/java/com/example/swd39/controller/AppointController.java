package com.example.swd39.controller;

import com.example.swd39.config.JwtProvider;
import com.example.swd39.exception.AppointmentException;
import com.example.swd39.exception.ServicesException;
import com.example.swd39.exception.TimeSlotsException;
import com.example.swd39.exception.UserException;
import com.example.swd39.model.Appointments;
import com.example.swd39.model.Role;
import com.example.swd39.model.Status;
import com.example.swd39.model.User;
import com.example.swd39.request.AppointmentRequest;
import com.example.swd39.service.AppointService;
import com.example.swd39.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/appoint")
public class AppointController {
    @Autowired
    private AppointService appointService;
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private UserService userService;
    // bệnh nhân tạo yêu cầu khám bệnh
    @PostMapping("/create-appointment")
     ResponseEntity<?> createAppointment(@RequestBody AppointmentRequest appointmentRequest, HttpServletRequest request) {
        String token = jwtProvider.getJwtFromRequest(request);
        String email = jwtProvider.getEmailFromToken(token);
        Role role = userService.findUserRoleByJwt(token);
        try {
            if(role == Role.patient) {
                return ResponseEntity.status(HttpStatus.CREATED).body(appointService.createAppointment(email, appointmentRequest));
            }
            else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
        } catch (UserException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (TimeSlotsException | ServicesException e) {
            throw new RuntimeException(e);
        }
    }

    //  nhân viên xem lịch hẹn của một bệnh nhân
//    @GetMapping("/view-appointment/{idUser}")
//    ResponseEntity<?> viewAppointment(@PathVariable("idUser") Long idUser, HttpServletRequest request) {
//        String token = jwtProvider.getJwtFromRequest(request);
//        Role role = userService.findUserRoleByJwt(token);
//        if(role == Role.patient || role == Role.employee) {
//            return ResponseEntity.ok(appointService.getPatientAppointment(idUser));
//        }
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//    }

    // bệnh nhân xem các lịch hẹn của mình
    @GetMapping("/view-appointment")
    ResponseEntity<?> viewAppointment(HttpServletRequest request) {
        String token = jwtProvider.getJwtFromRequest(request);
        User user = userService.findUserByEmail(jwtProvider.getEmailFromToken(token));
        if(user.getRole() == Role.patient || user.getRole() == Role.employee) {
            return ResponseEntity.ok(appointService.getPatientAppointment(user.getId()));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
    // bệnh nhân hủy lịch một cuộc hẹn đang pending
    @DeleteMapping("/delete-appointment/{idAppointment}")
    ResponseEntity<?> deleteAppointment(@PathVariable("idAppointment") Long idAppointment, HttpServletRequest request) {
        String token = jwtProvider.getJwtFromRequest(request);
        Role role = userService.findUserRoleByJwt(token);
        if(role == Role.patient) {
            appointService.deleteAppointment(idAppointment);
            return ResponseEntity.ok("deleted");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
    // bệnh nhân cập nhật lại thông tin lịch hẹn (chỉ với những lịch đang pending)
    @PostMapping("/update-appointment/{idAppointment}")
    ResponseEntity<?> updateAppointment(@PathVariable("idAppointment") Long idAppointment,@RequestBody AppointmentRequest appointmentRequest,HttpServletRequest request) throws AppointmentException {
        String token = jwtProvider.getJwtFromRequest(request);
        Role role = userService.findUserRoleByJwt(token);
        Appointments appointment = appointService.findById(idAppointment);
        if(role == Role.patient && appointment.getStatus() == Status.pending) {
            return ResponseEntity.ok(appointService.updateAppointment(idAppointment, appointmentRequest));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
    // danh sách các cuộc hẹn pending để nhân viên thao tác
    @GetMapping("/view-pendingAppointment")
    ResponseEntity<?> viewPendingAppointment(HttpServletRequest request) {
        String token = jwtProvider.getJwtFromRequest(request);
        Role role = userService.findUserRoleByJwt(token);
        if(role == Role.employee) {
            return ResponseEntity.ok(appointService.viewPendingAppointment());
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }


    // nhân viên ấn từ chối 1 cuộc hẹn pending
    @PostMapping("/reject-appointment/{idAppointment}")
    ResponseEntity<?> rejectAppointment(@PathVariable("idAppointment") Long idAppointment, HttpServletRequest request) throws AppointmentException {
        String token = jwtProvider.getJwtFromRequest(request);
        Role role = userService.findUserRoleByJwt(token);
        Appointments appointment = appointService.findById(idAppointment);
        if(role == Role.employee && appointment.getStatus() == Status.pending) {
            return ResponseEntity.ok(appointService.rejectAppointment(idAppointment));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    // nhân viên ấn chấp nhận 1 cuộc hẹn pending
    @PostMapping("/accept-appointment/{idAppointment}")
    ResponseEntity<?> acceptAppointment(@PathVariable("idAppointment") Long idAppointment, HttpServletRequest request) throws AppointmentException {
        String token = jwtProvider.getJwtFromRequest(request);
        Role role = userService.findUserRoleByJwt(token);
        Appointments appointment = appointService.findById(idAppointment);
        if(role == Role.employee && appointment.getStatus() == Status.pending) {
            return ResponseEntity.ok(appointService.acceptAppointment(idAppointment));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }


    // bác sĩ xem các cuộc hẹn đã được xác nhận bởi nhân viên

    @GetMapping("/view-acceptedAppointment")
    ResponseEntity<?> viewAcceptedAppointment(HttpServletRequest request) {
        String token = jwtProvider.getJwtFromRequest(request);
        User user = userService.findUserByEmail(jwtProvider.getEmailFromToken(token));
        if(user.getRole() == Role.doctor) {
            return ResponseEntity.ok(appointService.viewAcceptedAppointment(user.getId()));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
