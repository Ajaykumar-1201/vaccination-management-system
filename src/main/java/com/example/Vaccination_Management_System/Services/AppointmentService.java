package com.example.Vaccination_Management_System.Services;

import com.example.Vaccination_Management_System.Dtos.RequestDtos.AppointmentReqDto;
import com.example.Vaccination_Management_System.Exceptions.DoctorNotFound;
import com.example.Vaccination_Management_System.Exceptions.UserNotFound;
import com.example.Vaccination_Management_System.Models.Appointment;
import com.example.Vaccination_Management_System.Models.Doctor;
import com.example.Vaccination_Management_System.Models.User;
import com.example.Vaccination_Management_System.Repository.AppointmentRepository;
import com.example.Vaccination_Management_System.Repository.DoctorRepository;
import com.example.Vaccination_Management_System.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private UserRepository userRepository;
    public String bookAppointment(AppointmentReqDto appointmentReqDto) throws DoctorNotFound, UserNotFound {
        Optional<Doctor> doctorOptional = doctorRepository.findById(appointmentReqDto.getDocId());
        if(doctorOptional.isEmpty()) {
            throw new DoctorNotFound("DoctorId not found");
        }

        Optional<User> userOptional = userRepository.findById(appointmentReqDto.getUserId());
        if(userOptional.isEmpty()) {
            throw new DoctorNotFound("UserId not found");
        }

        Doctor doctor = doctorOptional.get();
        User user = userOptional.get();

        Appointment appointment = new Appointment();

        //Creating the object and setting of its attributes
        appointment.setAppointmentDate(appointmentReqDto.getAppointmentDate());
        appointment.setAppointmentTime(appointmentReqDto.getAppointmentTime());

        //Setting the foreign key attributes
        appointment.setDoctor(doctor);
        appointment.setUser(user);

        //Saving it before so that I can get the PK of the appointment table...
        appointment = appointmentRepository.save(appointment);

        doctor.getAppointmentList().add(appointment);
        user.getAppointmentList().add(appointment);

        doctorRepository.save(doctor);
        userRepository.save(user);

        return "Appointment booked successfully";
    }
}
