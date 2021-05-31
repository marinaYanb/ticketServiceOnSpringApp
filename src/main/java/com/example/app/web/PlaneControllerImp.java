package com.example.app.web;

import com.example.app.model.Plane;
import com.example.app.model.PlaneDto;
import com.example.app.model.Ticket;
import com.example.app.service.PlaneService;
import com.example.app.service.TicketService;
import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PlaneControllerImp implements PlaneController {

    final PlaneService planeService;
    final TicketService ticketService;
    final MapperFacade mapperFacade;

    public Page<PlaneDto> findPlanesFromNow(Pageable pageable) {
        Page<Plane> planePages = planeService.findPlanesFromNow(pageable);
        return planePages.map(plane -> mapperFacade.map(plane, PlaneDto.class));
    }

    public PlaneDto findPlaneById(@PathVariable Long planeId) {
        return mapperFacade.map(planeService.findPlaneById(planeId), PlaneDto.class);
    }

    public PlaneDto createPlane(@RequestBody Plane plane) {
        if (!plane.isDeleted()) {
            return mapperFacade.map(planeService.createPlane(plane), PlaneDto.class);
        }
        return null;
    }

    public void updatePlane(@PathVariable Long planeId, @RequestBody Plane plane) {
        plane.setId(planeId);
        planeService.createPlane(plane);
    }

    public void patchPlane(@PathVariable Long planeId) {
        Plane patchedAsDeleted = planeService.findPlaneById(planeId);
        patchedAsDeleted.setDeleted(true);
        List<Ticket> tickets = ticketService.findTicketsByPlaneId(planeId);
        for (Ticket ticket : tickets) {
            ticket.setDeleted(true);
        }
        planeService.createPlane(patchedAsDeleted);
    }
}
