package com.example.app.web;

import com.example.app.model.Plane;
import com.example.app.model.PlaneDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

public interface PlaneController {

    @GetMapping("/planes")
    Page<PlaneDto> findPlanesFromNow(Pageable pageable);

    @GetMapping("/planes/{planeId}")
    PlaneDto findPlaneById(@PathVariable Long planeId);

    @PostMapping("/planes")
    PlaneDto createPlane(@RequestBody Plane plane);

    @PutMapping("/planes/{planeId}")
    void updatePlane(@PathVariable Long planeId, @RequestBody Plane plane);

    @PatchMapping("/planes/{planeId}")
    void patchPlane(@PathVariable Long planeId);

}
