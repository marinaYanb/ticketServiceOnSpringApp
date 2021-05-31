package com.example.app.service;

import com.example.app.model.Plane;
import com.example.app.repository.PlaneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@RequiredArgsConstructor
@Service
public class PlaneServiceImpl implements PlaneService {

    @Autowired
    private PlaneRepository planeRepository;
    @Autowired
    private TicketService ticketService;

    public Plane findPlaneById(Long id) {
        return planeRepository.findById(id).orElse(null);
    }

    public Plane createPlane(Plane plane) {
        Plane newPlane = planeRepository.save(plane);
        for (int i = 0; i < plane.getPlaces(); i++) {
            ticketService.createTickets(newPlane);
        }
        return newPlane;
    }

    public Page<Plane> findPlanesFromNow(Pageable pageable) {
        Page<Plane> test = planeRepository.findPlanesFromNow(LocalDate.now(), pageable);
        return test;
    }
}
