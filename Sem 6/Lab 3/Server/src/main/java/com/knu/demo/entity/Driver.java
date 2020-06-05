package com.knu.demo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Table;

import javax.persistence.*;

@Data
@Entity
@Table(appliesTo = "drivers")
@NoArgsConstructor
public class Driver {
    @Id
    @GeneratedValue(generator = "drivers_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "drivers_id_seq", sequenceName = "drivers_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @OneToOne
    private Car car;

}
