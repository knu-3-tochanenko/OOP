package com.knu.demo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "bookings")
@NoArgsConstructor
public class Booking {
    @Id
    @GeneratedValue(generator = "bookings_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "bookings_id_seq", sequenceName = "bookings_id_seq", allocationSize = 1)
    @Column(name = "id", updatable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "min_class")
    private CarClass minClass;

    @Column(name = "depart")
    private String depart;

    @Column(name = "destination")
    private String destination;

    @Column(name = "min_seats")
    private Integer minSeats;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private RideStatus rideStatus;

    @ManyToOne
    private User user;
}
