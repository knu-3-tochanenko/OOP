package com.knu.demo.service.data;

import com.knu.demo.entity.Booking;
import com.knu.demo.entity.Car;
import com.knu.demo.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarService {
    private final CarRepository carRepository;

    public List<Car> findCarByBooking(Booking booking) {
        return carRepository.findCarsByCarClassGreaterThanAndSeatsNumberGreaterThanAndServiceable(booking.getMinClass(), booking.getMinSeats(), true);
    }

    public Car save(Car car) {
        return carRepository.save(car);
    }

}
