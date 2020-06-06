package com.knu.demo.service;

import com.knu.demo.converter.CarConverter;
import com.knu.demo.dto.CarDTO;
import com.knu.demo.service.data.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CarControllerService {
    private final CarService carService;

    private final CarConverter carConverter;

}
