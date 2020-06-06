package com.knu.demo.converter;

import com.knu.demo.dto.CarDTO;
import com.knu.demo.entity.Car;
import com.knu.demo.entity.CarClass;
import org.springframework.stereotype.Component;

@Component
public class CarConverter {
    public CarDTO convertToDTO(Car car) {
        CarDTO carDTO = new CarDTO();
        carDTO.setCarClass(car.getCarClass().toString());
        carDTO.setSeatsNumber(car.getSeatsNumber());
        carDTO.setLastInspection(car.getLastInspection());
        carDTO.setServiceable(car.getServiceable());

        return carDTO;
    }

    public Car convertToEntiry(CarDTO carDTO) {
        Car car = new Car();
        car.setCarClass(CarClass.valueOf(carDTO.getCarClass()));
        car.setSeatsNumber(carDTO.getSeatsNumber());
        car.setLastInspection(carDTO.getLastInspection());
        car.setServiceable(carDTO.getServiceable());

        return car;
    }
}
