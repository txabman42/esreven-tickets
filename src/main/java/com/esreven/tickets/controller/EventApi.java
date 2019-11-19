package com.esreven.tickets.controller;

import com.esreven.tickets.model.EventDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.io.IOException;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Api(value = "Events", tags={ "events" })
public interface EventApi {

    @ApiOperation(value = "Creates a new event", nickname = "addEvent", notes = "", response = EventDto.class)
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Created", response = EventDto.class),
        @ApiResponse(code = 400, message = "Bad request"),
        @ApiResponse(code = 401, message = "Unauthorized") })
    @PostMapping(value = "/events",
        produces = { "application/json" },
        consumes = { "application/json" })
    ResponseEntity<EventDto> addEvent(@Valid @RequestBody EventDto body) throws IOException;

//    @ApiOperation(value = "Reads an event by id", nickname = "getEventById",
//        notes = "Returns all the information of that event and the related listings", response = Event.class)
//    @ApiResponses(value = {
//        @ApiResponse(code = 200, message = "Successful operation", response = Event.class),
//        @ApiResponse(code = 400, message = "Invalid id supplied"),
//        @ApiResponse(code = 401, message = "Unauthorized"),
//        @ApiResponse(code = 404, message = "Event not found") })
//    @GetMapping(value = "/events/{id}", produces = { "application/json" })
//    ResponseEntity<EventDto> getEventById(@ApiParam(value = "Id of the event to return",required=true) @PathVariable("id") String id);

}
