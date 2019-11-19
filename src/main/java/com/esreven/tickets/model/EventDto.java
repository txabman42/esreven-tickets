package com.esreven.tickets.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Data
@Builder
@Validated
public class EventDto {

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

    @JsonProperty("listings")
    @Valid
    private List<TicketDto> listings;

}
