package com.knu.demo.service.data;

import com.knu.demo.entity.Ride;
import com.knu.demo.entity.RideStatus;
import com.knu.demo.repository.RideRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RideService {
    private final RideRepository rideRepository;

    public Ride save(Ride ride) {
        return rideRepository.save(ride);
    }

    public List<Ride> getRidesByCar(Long carId) {
        return rideRepository.findByCarIdAndBooking_RideStatus(carId, RideStatus.WAITING);
    }
}
