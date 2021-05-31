package com.example.app.model;

import lombok.Data;

@Data
public class UserDtoWithoutTickets {
    private Long id;
    private String firstName;
    private String lastName;
    private String passport;
    private boolean deleted;
}