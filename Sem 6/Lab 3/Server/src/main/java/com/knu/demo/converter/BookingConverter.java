package com.knu.demo.converter;

import com.knu.demo.dto.BookingDTO;
import com.knu.demo.entity.Booking;
import com.knu.demo.entity.CarClass;
import com.knu.demo.entity.RideStatus;

public class BookingConverter {
    public BookingDTO convertToDTO(Booking booking) {
        BookingDTO bookingDTO = new BookingDTO();
        bookingDTO.setId(booking.getId());
        bookingDTO.setDepart(booking.getDepart());
        bookingDTO.setDestination(booking.getDestination());
        bookingDTO.setMinClass(booking.getMinClass().toString());
        bookingDTO.setMinSeats(booking.getMinSeats());
        bookingDTO.setStatus(booking.getRideStatus().toString());

        return bookingDTO;
    }

    public Booking convertToEntity(BookingDTO bookingDTO) {
        Booking booking = new Booking();
        booking.setId(bookingDTO.getId());
        booking.setDepart(bookingDTO.getDepart());
        booking.setDestination(bookingDTO.getDestination());
        booking.setMinClass(CarClass.valueOf(bookingDTO.getMinClass()));
        booking.setMinSeats(bookingDTO.getMinSeats());
        booking.setRideStatus(RideStatus.valueOf(bookingDTO.getStatus()));

        return booking;
    }

    //public CarDTO convertToDTO(Car car) {
    //        CarDTO carDTO = new CarDTO();
    //        carDTO.setCarClass(car.getCarClass().toString());
    //        carDTO.setSeatsNumber(car.getSeatsNumber());
    //        carDTO.setLastInspection(car.getLastInspection());
    //        carDTO.setServiceable(car.getServiceable());
    //
    //        return carDTO;
    //    }
    //
    //    public Car convertToEntiry(CarDTO carDTO) {
    //        Car car = new Car();
    //        car.setCarClass(CarClass.valueOf(carDTO.getCarClass()));
    //        car.setSeatsNumber(carDTO.getSeatsNumber());
    //        car.setLastInspection(carDTO.getLastInspection());
    //        car.setServiceable(carDTO.getServiceable());
    //
    //        return car;
    //    }
}
