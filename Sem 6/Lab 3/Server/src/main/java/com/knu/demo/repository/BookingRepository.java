package com.knu.demo.repository;

import com.knu.demo.entity.Booking;
import com.knu.demo.entity.RideStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByUserId(Long userId);

    List<Booking> findByRideStatus(RideStatus status);
}
