package com.esreven.tickets.controller;

import com.esreven.tickets.model.EventDto;
import com.esreven.tickets.service.EventService;
import io.swagger.annotations.Api;
import java.io.IOException;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

@Slf4j
@Controller
@Api(value = "Events", tags={ "events" })
public class EventApiImp implements EventApi {

    private final EventService eventService;

    public EventApiImp(EventService eventService) {
        this.eventService = eventService;
    }

    public ResponseEntity<EventDto> addEvent(@Valid @RequestBody EventDto body) throws IOException {
        log.info("[ POST ] -> /events Body: {}", body);
        EventDto eventDto = eventService.save(body);
        return ResponseEntity.status(HttpStatus.CREATED).body(eventDto);
    }

}
