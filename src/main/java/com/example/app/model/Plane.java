package com.example.app.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.Duration;
import java.time.LocalDate;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Plane {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column
    private Long id;

    @Column
    private String name;

    @Column
    private Integer places;

    @Column(name = "departure_time")
    private LocalDate departureTime;

    @Column
    private Duration duration;

    @Column(name = "departure_from")
    private String departurePlace;

    @Column(name = "destination")
    private String destination;

    @Column
    private boolean deleted;

    public Plane(String name, Integer places,
                 LocalDate departureTime,
                 Duration duration,
                 String departurePlace,
                 String destination,
                 boolean deleted) {

        this.name = name;
        this.places = places;
        this.departureTime = departureTime;
        this.duration = duration;
        this.departurePlace = departurePlace;
        this.destination = destination;
        this.deleted = deleted;
    }
}
