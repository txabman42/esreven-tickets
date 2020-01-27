package com.esreven.tickets.client;

import com.esreven.tickets.model.EventTicketDto;

public interface EventClient {

    EventTicketDto getTicketsPerEvent(String eventId);
}
