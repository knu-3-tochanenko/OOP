package com.knu.demo.service;

import com.knu.demo.converter.BookingConverter;
import com.knu.demo.converter.CarConverter;
import com.knu.demo.dto.BookingDTO;
import com.knu.demo.dto.CarDTO;
import com.knu.demo.service.data.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarControllerService {
    private final CarService carService;

    private final CarConverter carConverter;
    private final BookingConverter bookingConverter;

    public List<CarDTO> findCarByBooking(BookingDTO bookingDTO) {
        return carConverter.convertToListDTO(carService.findCarByBooking(bookingConverter.convertToEntity(bookingDTO)));
    }

}
