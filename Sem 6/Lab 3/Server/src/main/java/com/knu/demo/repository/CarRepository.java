package com.knu.demo.repository;

import com.knu.demo.entity.Car;
import com.knu.demo.entity.CarClass;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {
    public List<Car> findCarsByCarClassGreaterThanAndSeatsNumberGreaterThanAndServiceable(CarClass carClass, int seats, boolean serviceable);
}
