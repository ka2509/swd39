package com.example.swd39.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentRequest {
    private Long doctorId;
    private Long serviceId;
    private Long timeId;
    private String note;
}
