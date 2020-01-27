package com.esreven.tickets.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import javax.validation.Valid;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@Validated
public class EventTicketDto extends EventDto {

    @Builder(builderMethodName = "EventTicketDto")
    public EventTicketDto(Long eventId, Long totalListings, Long totalTickets, int minQuantity,
        int maxQuantity, int start, int rows, List<TicketDto> ticketDto) {
        super(eventId, totalListings, totalTickets, minQuantity, maxQuantity, start, rows);
        this.ticketDto = ticketDto;
    }

    @JsonProperty("listings")
    @Valid
    private List<TicketDto> ticketDto;
}
