package com.example.app.web;

import com.example.app.model.Ticket;
import com.example.app.model.TicketDto;
import com.example.app.service.TicketService;
import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TicketControllerImpl implements TicketController {

    private final TicketService ticketService;
    final MapperFacade mapperFacade;

    public List<TicketDto> findTicketsByPlaneId(@PathVariable Long planeId) {
        return mapperFacade.mapAsList(ticketService.findTicketsByPlaneId(planeId), TicketDto.class);
    }

    public TicketDto findTicketById(@PathVariable Long ticketId) {
        return mapperFacade.map(ticketService.findTicketById(ticketId), TicketDto.class);
    }

    public void updateTicket(@PathVariable Long ticketId, @RequestBody Ticket ticket) {
        ticket.setId(ticketId);
        ticketService.createTicket(ticket);
    }

    public void patchTicket(@PathVariable Long ticketId) {
        Ticket patchedAsDeleted = ticketService.findTicketById(ticketId);
        patchedAsDeleted.setDeleted(true);
        ticketService.createTicket(patchedAsDeleted);
    }
}

