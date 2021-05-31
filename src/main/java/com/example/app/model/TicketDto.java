package com.example.app.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TicketDto extends TicketDtoWithoutUser{

    private Long id;
    private Plane plane;
    private BigDecimal price;
    private UserDtoWithoutTickets user;
    private boolean deleted;
}