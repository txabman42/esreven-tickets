package com.esreven.tickets.service;

import com.esreven.tickets.model.EventDto;
import java.io.IOException;

public interface EventService {

    EventDto save(EventDto eventDto) throws IOException;
}
