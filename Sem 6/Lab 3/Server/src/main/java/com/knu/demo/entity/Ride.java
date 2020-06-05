package com.knu.demo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Table;

import javax.persistence.*;

@Data
@Entity
@Table(appliesTo = "rides")
@NoArgsConstructor
public class Ride {
    @Id
    @GeneratedValue(generator = "rides_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "rides_id_seq", sequenceName = "rides_id_seq", allocationSize = 1)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "price")
    private int price;

    @OneToOne
    private Car car;

    @OneToOne
    private Booking booking;
}
