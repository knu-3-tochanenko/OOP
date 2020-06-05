package com.knu.demo.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class BookingDTO {
    @NotNull(message = "Car min class required")
    private String minClass;

    @NotNull(message = "Depart is required")
    private String depart;

    @NotNull(message = "Destination is required")
    private String destination;

    @NotNull(message = "Min number of seats is required")
    private String minSeats;

    @NotNull(message = "Min number of seats is required")
    private String status;
}
