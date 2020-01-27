package com.esreven.tickets.service;

import com.esreven.tickets.model.TicketEventDto;
import java.io.IOException;

public interface TicketService {

    TicketEventDto save(TicketEventDto ticketEventDto) throws IOException;
}
