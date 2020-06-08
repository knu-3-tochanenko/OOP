package com.knu.demo.service;

import com.knu.demo.converter.RideConverter;
import com.knu.demo.dto.RideDTO;
import com.knu.demo.entity.Booking;
import com.knu.demo.entity.Car;
import com.knu.demo.exception.BookingNotFoundException;
import com.knu.demo.exception.CarNotFoundException;
import com.knu.demo.service.data.BookingService;
import com.knu.demo.service.data.CarService;
import com.knu.demo.service.data.RideService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RideControllerService {
    private final RideService rideService;
    private final BookingService bookingService;
    private final CarService carService;

    private final RideConverter rideConverter;

    public RideDTO save(RideDTO rideDTO) {
        Optional<Booking> booking = bookingService.findBookingById(rideDTO.getBookingId());
        if (!booking.isPresent()) {
            throw new BookingNotFoundException("Booking with id " + rideDTO.getBookingId() + " not found");
        }
        Optional<Car> car = carService.findCarById(rideDTO.getCarId());
        if (!car.isPresent()) {
            throw new CarNotFoundException("Car with id " + rideDTO.getCarId() + " not found");
        }
        return rideConverter.convertToDTO(rideService.save(rideConverter.convertToEntity(rideDTO, booking.get(), car.get())));
    }

    public List<RideDTO> getRidesByCar(Long carId) {
        return rideConverter.convertToListDTO(rideService.getRidesByCar(carId));
    }
}
