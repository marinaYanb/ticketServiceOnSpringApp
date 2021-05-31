package com.example.app.service;

import com.example.app.model.Plane;
import com.example.app.model.Ticket;
import com.example.app.model.User;
import com.example.app.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final UserService userService;

    public List<Ticket> findAllTickets() {
        return ticketRepository.findAll();
    }

    public Ticket findTicketById(Long ticketId) {
        return ticketRepository.findTicketById(ticketId);
    }

    public void createTickets(Plane plane) {
        User user = userService.findUserById(1l);
        ticketRepository.createTickets(plane.getId(), user.getId());
    }

    public void createTicket(Ticket ticket) {
        ticketRepository.updateTicket(ticket.getPlane(), ticket.getUser(), ticket.getPrice(), ticket.isDeleted(), ticket.getId());

    }

    public List<Ticket> findTicketsByPlaneId(Long planeId) {
        return ticketRepository.findTicketsByPlaneId(planeId);
    }
}
