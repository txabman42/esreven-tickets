package com.esreven.tickets.model;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    private Long totalListings;

    @JsonProperty("totalTickets")
    private Long totalTickets;

    @JsonProperty("minQuantity")
    private int minQuantity;

    @JsonProperty("maxQuantity")
    private int maxQuantity;

    @JsonProperty("start")
    private int start;

    @JsonProperty("rows")
    private int rows;

}
