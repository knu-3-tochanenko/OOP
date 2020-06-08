package com.knu.demo.repository;

import com.knu.demo.entity.Ride;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RideRepository extends JpaRepository<Ride, Long> {
    List<Ride> findByCarId(Long carId);
}
