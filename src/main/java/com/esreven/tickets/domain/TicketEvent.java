package com.esreven.tickets.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TicketEvent {

    private static final long serialVersionUID = -1L;

    private Ticket ticket;
    private Event event;
}
