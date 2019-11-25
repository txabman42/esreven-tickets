package com.esreven.tickets.controller;

import com.esreven.tickets.model.EventDto;
import com.esreven.tickets.model.EventTicketDto;
import com.esreven.tickets.model.TicketEventDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.io.IOException;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Api(value = "Events", tags={ "events" })
public interface EventApi {

    @ApiOperation(value = "Creates a new event", nickname = "addEvent", response = EventDto.class)
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Created", response = EventDto.class),
        @ApiResponse(code = 400, message = "Bad request"),
        @ApiResponse(code = 401, message = "Unauthorized") })
    @PostMapping(value = "/event/tickets",
        produces = { "application/json" },
        consumes = { "application/json" })
    ResponseEntity<List<TicketEventDto>> addEvent(@Valid @RequestBody EventTicketDto body)
        throws IOException;

}
