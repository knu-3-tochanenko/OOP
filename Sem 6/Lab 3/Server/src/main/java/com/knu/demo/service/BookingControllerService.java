package com.knu.demo.service;

import com.knu.demo.converter.BookingConverter;
import com.knu.demo.dto.BookingDTO;
import com.knu.demo.entity.Booking;
import com.knu.demo.entity.RideStatus;
import com.knu.demo.service.data.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingControllerService {
    private final BookingConverter bookingConverter;
    private final BookingService bookingService;
    private final ApplicationEventPublisher applicationEventPublisher;

    public BookingDTO save(BookingDTO bookingDTO) {
        Booking currentBooking = bookingConverter.convertToEntity(bookingDTO);
        BookingDTO savedUserDto = bookingConverter.convertToDTO(bookingService.save(currentBooking));
        applicationEventPublisher.publishEvent(savedUserDto);
        return savedUserDto;
    }

    public List<BookingDTO> findBookingsByStatus(String bookingStatus) {
        return bookingConverter.convertToListDTO(bookingService.findBookingByStatus(RideStatus.valueOf(bookingStatus)));
    }
}
