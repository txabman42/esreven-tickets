package com.esreven.tickets.service;

import com.esreven.tickets.domain.Ticket;
import java.io.IOException;

public interface TicketService {

    Ticket save(Ticket ticket) throws IOException;
}
