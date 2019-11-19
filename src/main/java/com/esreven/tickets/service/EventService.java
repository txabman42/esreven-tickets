package com.esreven.tickets.service;

import com.esreven.tickets.model.EventDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.io.IOException;
import java.util.Optional;

public interface EventService {
    EventDto save(EventDto eventDto) throws IOException;

//    Optional<EventDto> findOne(String id);
//
//    Iterable<EventDto> findAll();
//
//    long count();
//
//    void delete(EventDto eventDto);
}
