package com.knu.demo.service.data;

import com.knu.demo.entity.Booking;
import com.knu.demo.entity.RideStatus;
import com.knu.demo.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingService {
    private final BookingRepository bookingRepository;

    public Booking save(Booking booking) {
        return bookingRepository.save(booking);
    }

    public List<Booking> findUserBookings(Long userId) {
        return bookingRepository.findByUserId(userId);
    }

    public List<Booking> findBookingByStatus(RideStatus status) {
        return bookingRepository.findByRideStatus(status);
    }
}
