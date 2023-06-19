package com.example.Vaccination_Management_System.Dtos.RequestDtos;

import lombok.Data;

import java.time.LocalTime;
import java.util.Date;

@Data
public class AppointmentReqDto {
    private int docId;

    private Integer userId;

    private Date appointmentDate;

    private LocalTime appointmentTime;
}
