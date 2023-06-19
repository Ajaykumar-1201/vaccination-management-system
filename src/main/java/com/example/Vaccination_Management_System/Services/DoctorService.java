package com.example.Vaccination_Management_System.Services;

import com.example.Vaccination_Management_System.Exceptions.DoctorAlreadyExistsException;
import com.example.Vaccination_Management_System.Exceptions.EmailIdEmptyException;
import com.example.Vaccination_Management_System.Models.Doctor;
import com.example.Vaccination_Management_System.Repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoctorService {
    @Autowired
    DoctorRepository doctorRepository;
    public String addDoctor(Doctor doctor) throws EmailIdEmptyException, DoctorAlreadyExistsException{

        //validation part is over
        if(doctor.getEmailId() == null){
            throw new EmailIdEmptyException("Email id is mandatory");
        }

        if(doctorRepository.findByEmailId(doctor.getEmailId()) != null){
            throw new DoctorAlreadyExistsException("Doctor with this emailId already exits.");
        }

        //saving the doctor
        doctorRepository.save(doctor);
        return "Doctor has been added to the database";
    }
}
