package com.knu.demo.controller;

import com.knu.demo.dto.BookingDTO;
import com.knu.demo.dto.CarDTO;
import com.knu.demo.service.CarControllerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequiredArgsConstructor
public class CarController {
    private final CarControllerService carService;

    @GetMapping(value = "/car/booking")
    public List<CarDTO> getCarsByBooking(@Valid @RequestBody BookingDTO bookingDTO) {
        return carService.findCarByBooking(bookingDTO);
    }
}
