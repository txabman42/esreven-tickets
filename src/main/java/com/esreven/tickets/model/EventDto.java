package com.esreven.tickets.model;

import com.esreven.tickets.domain.Ticket;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Data
@Builder
@Validated
public class EventDto extends  {

    @JsonProperty("eventId")
    @NotNull
    private Long eventId;

    @JsonProperty("totalListings")
    @NotNull
    private Long totalListings;

    @JsonProperty("totalTickets")
    @NotNull
    private Long totalTickets;

    @JsonProperty("minQuantity")
    @NotNull
    private int minQuantity;

    @JsonProperty("maxQuantity")
    @NotNull
    private int maxQuantity;

    @JsonProperty("start")
    @NotNull
    private int start;

    @JsonProperty("rows")
    @NotNull
    private int rows;

}
