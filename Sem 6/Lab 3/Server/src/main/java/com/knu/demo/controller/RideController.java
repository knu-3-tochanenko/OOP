package com.knu.demo.controller;

import com.knu.demo.dto.RideDTO;
import com.knu.demo.service.RideControllerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequiredArgsConstructor
public class RideController {
    private final RideControllerService rideService;

    @PostMapping(value = "/ride")
    public RideDTO saveRide(@Valid @RequestBody RideDTO rideDTO) {
        return rideService.save(rideDTO);
    }

    @GetMapping(value = "/ride/{carId}")
    public List<RideDTO> getRidesByCar(@PathVariable Long carId) {
        return rideService.getRidesByCar(carId);
    }
}
