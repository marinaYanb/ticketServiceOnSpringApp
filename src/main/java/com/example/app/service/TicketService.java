package com.example.app.service;

import com.example.app.model.Plane;
import com.example.app.model.Ticket;

import java.util.List;

public interface TicketService {

    List<Ticket> findAllTickets();

    Ticket findTicketById(Long ticketId);

    void createTickets(Plane plane);

    void createTicket(Ticket ticket);

    List<Ticket> findTicketsByPlaneId(Long planeId);
}
