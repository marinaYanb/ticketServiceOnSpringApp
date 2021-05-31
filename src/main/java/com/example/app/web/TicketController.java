package com.example.app.web;

import com.example.app.model.Ticket;
import com.example.app.model.TicketDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface TicketController {

    @GetMapping("planes/{planeId}/tickets")
    List<TicketDto> findTicketsByPlaneId(@PathVariable Long planeId);

    @GetMapping("tickets/{ticketId}")
    TicketDto findTicketById(@PathVariable Long ticketId);

    @PutMapping("/tickets/{ticketId}")
    void updateTicket(@PathVariable Long ticketId, @RequestBody Ticket ticket);

    @PatchMapping("/tickets/{ticketId}")
    void patchTicket(@PathVariable Long ticketId);
}
