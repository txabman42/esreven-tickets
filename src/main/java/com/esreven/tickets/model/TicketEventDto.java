package com.esreven.tickets.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.Valid;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@Builder
@Validated
public class TicketEventDto {

    @JsonProperty("ticket")
    @Valid
    private TicketDto ticketDto;

    @JsonProperty("event")
    @Valid
    private EventDto eventDto;
}
