package com.example.app.service;

import com.example.app.model.Plane;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PlaneService {

    Plane findPlaneById(Long id);

    Plane createPlane(Plane plane);

    Page<Plane> findPlanesFromNow(Pageable pageable);
}
