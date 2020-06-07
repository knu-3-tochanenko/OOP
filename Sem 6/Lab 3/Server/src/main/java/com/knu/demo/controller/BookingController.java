package com.knu.demo.controller;

import com.knu.demo.dto.BookingDTO;
import com.knu.demo.service.BookingControllerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequiredArgsConstructor
public class BookingController {
    private final BookingControllerService bookingService;

    @PostMapping(value = "/booking")
    public ResponseEntity<String> addBooking(@Valid @RequestBody BookingDTO bookingDTO) {
        bookingService.save(bookingDTO);
        return ResponseEntity.accepted().build();
    }

    @GetMapping(value = "/booking/user/{userId}")
    public List<BookingDTO> getBookingsByUser(@PathVariable Long userId) {
        return bookingService.findBookingsByUser(userId);
    }

    @GetMapping(value = "/booking/status/{status}")
    public List<BookingDTO> getBookingsByStatus(@PathVariable String status) {
        return bookingService.findBookingsByStatus(status);
    }
}
