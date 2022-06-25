package com.example.vazifa_01_mazu_01.repository;

import com.example.vazifa_01_mazu_01.entity.Worker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkerRepository extends JpaRepository<Worker,Integer> {

    boolean existsByPhoneNumber(String phoneNumber);
}
