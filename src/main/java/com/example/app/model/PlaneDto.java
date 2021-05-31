package com.example.app.model;

import lombok.Data;

import java.time.Duration;
import java.time.LocalDate;

@Data
public class PlaneDto {
    private Long id;
    private String name;
    private Integer places;
    private LocalDate departureTime;
    private Duration duration;
    private String departurePlace;
    private String destination;
    private boolean deleted;
}
