package com.example.app.model;

import lombok.Data;

import java.util.List;

@Data
public class UserDto extends UserDtoWithoutTickets{
    private Long id;
    private String firstName;
    private String lastName;
    private String passport;
    private List<TicketDtoWithoutUser> tickets;
    private boolean deleted;
}