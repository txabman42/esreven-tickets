package com.esreven.tickets.controller;

import com.esreven.tickets.model.EventTicketDto;
import com.esreven.tickets.model.TicketDto;
import com.esreven.tickets.model.TicketEventDto;
import com.esreven.tickets.service.TicketService;
import io.swagger.annotations.Api;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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

    private final TicketService ticketService;

    public EventApiImp(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    public ResponseEntity<List<TicketEventDto>> addEvent(@Valid @RequestBody EventTicketDto body)
        throws IOException {
        log.info("[ POST ] -> /events Body: {}", body);
        List<TicketEventDto> ticketEventDtos = new ArrayList<>();
        for (TicketDto ticketDto : body.getTicketDto()) {
            TicketEventDto ticketEventDto = TicketEventDto.builder()
                .ticketDto(ticketDto)
                .eventDto(body)
                .build();

            ticketEventDtos.add(ticketService.save(ticketEventDto));
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(ticketEventDtos);
    }

}
