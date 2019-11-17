package com.esreven.tickets.service;

import com.esreven.tickets.domain.Event;
import java.util.Optional;

public interface EventService {
    Event save(Event event);

    Optional<Event> findOne(String id);

    Iterable<Event> findAll();

    long count();

    void delete(Event event);
}
