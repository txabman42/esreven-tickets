package com.esreven.tickets.domain;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Event {

    private UUID id;
    private Long eventId;
    private Long totalListing;
    private Long totalTickets;
    private int minQuantity;
    private int maxQuantity;
    private int start;
    private int rows;

}
