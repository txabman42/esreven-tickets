package com.esreven.tickets.service;

import com.esreven.tickets.domain.Event;
import com.esreven.tickets.repository.EventRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventServiceImp implements EventService {

    private final EventRepository eventRepository;

    @Autowired
    public EventServiceImp(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public Event save(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public Optional<Event> findOne(String id) {
        return eventRepository.findById(id);
    }

    @Override
    public Iterable<Event> findAll() {
        return eventRepository.findAll();
    }

    @Override
    public long count() {
        return eventRepository.count();
    }

    @Override
    public void delete(Event event) {
        eventRepository.delete(event);
    }
}
