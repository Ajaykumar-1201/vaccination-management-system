package com.example.Vaccination_Management_System.Repository;

import com.example.Vaccination_Management_System.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
