package com.esreven.tickets.service;

import com.esreven.tickets.domain.Event;
import com.esreven.tickets.domain.Location;
import com.esreven.tickets.domain.Price;
import com.esreven.tickets.domain.Ticket;
import com.esreven.tickets.domain.TicketEvent;
import com.esreven.tickets.model.EventDto;
import com.esreven.tickets.model.LocationDto;
import com.esreven.tickets.model.PriceDto;
import com.esreven.tickets.model.TicketDto;
import com.esreven.tickets.model.TicketEventDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.rest.RestStatus;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TicketServiceImp implements TicketService {

    private static final String INDEX = "ticket";

    private RestHighLevelClient client;
    private ObjectMapper objectMapper;

    public TicketServiceImp(RestHighLevelClient client, ObjectMapper objectMapper) {
        this.client = client;
        this.objectMapper = objectMapper;
    }

    @Override
    public TicketEventDto save(TicketEventDto ticketEventDto) throws IOException {
        TicketEvent ticketEvent = assembleTicketEventDto(ticketEventDto);
        persistElasticsearch(ticketEvent);

        return assembleTicketEvent(ticketEvent);
    }

    private void persistElasticsearch(TicketEvent ticketEvent) throws IOException {
        String ticketEventString = objectMapper.writeValueAsString(ticketEvent);

        IndexRequest request = new IndexRequest(INDEX);
        request.id(ticketEvent.getTicket().getId().toString());
        request.source(ticketEventString, XContentType.JSON);

        IndexResponse response = client.index(request, RequestOptions.DEFAULT);

        if (response.status() == RestStatus.CREATED) {
            log.info("Index request {} for {} event successfully created", request.id(),
                ticketEvent.getTicket().getId());
        }
    }

    private TicketEventDto assembleTicketEvent(TicketEvent ticketEvent) {
        return TicketEventDto.builder()
            .ticketDto(assembleTicket(ticketEvent.getTicket()))
            .eventDto(assembleEvent(ticketEvent.getEvent()))
            .build();
    }

    private TicketEvent assembleTicketEventDto(TicketEventDto ticketEventDto) {
        TicketEvent ticketEvent = new TicketEvent();
        ticketEvent.setEvent(assembleEventDto(ticketEventDto.getEventDto()));
        ticketEvent.setTicket(assembleTicketDto(ticketEventDto.getTicketDto()));

        return ticketEvent;
    }

    private Event assembleEventDto(EventDto eventDto) {
        Event event = new Event();
        event.setId(UUID.randomUUID());
        event.setEventId(eventDto.getEventId());
        event.setTotalListing(eventDto.getTotalListings());
        event.setTotalTickets(eventDto.getTotalTickets());
        event.setMinQuantity(eventDto.getMinQuantity());
        event.setMaxQuantity(eventDto.getMaxQuantity());
        event.setStart(eventDto.getStart());
        event.setRows(eventDto.getRows());

        return event;
    }

    private EventDto assembleEvent(Event event) {
        return EventDto.builder()
            .eventId(event.getEventId())
            .totalListings(event.getTotalListing())
            .totalTickets(event.getTotalTickets())
            .minQuantity(event.getMinQuantity())
            .maxQuantity(event.getMaxQuantity())
            .start(event.getStart())
            .rows(event.getRows())
            .build();
    }

    private TicketDto assembleTicket(Ticket ticket) {
        if (ticket.getFaceValue() != null) {
            PriceDto priceDto = PriceDto.builder()
                .currency(ticket.getFaceValue().getCurrency())
                .build();
        }

        PriceDto priceDto = ticket.getFaceValue() != null
            ? PriceDto.builder().currency(ticket.getFaceValue().getCurrency()).build()
            : null;

        return TicketDto.builder()
            .listingId(ticket.getListingId())
            .sectionId(ticket.getSectionId())
            .quantity(ticket.getQuantity())
            .sellerSectionName(ticket.getSellerSectionName())
            .sectionName(ticket.getSectionName())
            .zoneId(ticket.getZoneId())
            .deliveryTypeList(ticket.getDeliveryTypeList())
            .isGA(ticket.getIsGA())
            .splitOption(ticket.getSplitOption())
            .sellerOwnInd(ticket.getSellerOwnInd())
            .faceValue(priceDto)
            .isSectionMapped(ticket.getIsSectionMapped())
            .pricePerProduct(PriceDto.builder()
                .amount(ticket.getPricePerProduct().getAmount())
                .currency(ticket.getPricePerProduct().getCurrency())
                .build())
            .zone(ticket.getZone())
            .splitQuantity(ticket.getSplitQuantity())
            .buyQuantityOptions(ticket.getBuyQuantityOptions())
            .locationDtos(ticket.getLocations().stream().map(
                q -> LocationDto.builder()
                    .row(q.getRow())
                    .seat(q.getSeat())
                    .build()
            ).collect(Collectors.toList()))
            .build();
    }

    private Ticket assembleTicketDto(TicketDto ticketDto) {
        Ticket ticket = new Ticket();
        ticket.setId(UUID.randomUUID());
        ticket.setListingId(ticketDto.getListingId());
        ticket.setSectionId(ticketDto.getSectionId());
        ticket.setQuantity(ticketDto.getQuantity());
        ticket.setSellerSectionName(ticketDto.getSellerSectionName());
        ticket.setSectionName(ticketDto.getSectionName());
        ticket.setZoneId(ticketDto.getZoneId());
        ticket.setDeliveryTypeList(ticketDto.getDeliveryTypeList());
        ticket.setIsGA(ticketDto.getIsGA());
        ticket.setSplitOption(ticketDto.getSplitOption());
        ticket.setSellerOwnInd(ticketDto.getSellerOwnInd());
        if (ticketDto.getFaceValue() != null) {
            ticket.setFaceValue(new Price(ticketDto.getFaceValue().getAmount(),
                ticketDto.getFaceValue().getCurrency()));
        }
        ticket.setIsSectionMapped(ticketDto.getIsSectionMapped());
        ticket.setPricePerProduct(new Price(ticketDto.getPricePerProduct().getAmount(),
            ticketDto.getPricePerProduct().getCurrency()));
        ticket.setZone(ticketDto.getZone());
        ticket.setSplitQuantity(ticketDto.getSplitQuantity());
        ticket.setBuyQuantityOptions(ticketDto.getBuyQuantityOptions());
        ticket.setLocations(extractLocationDtos(ticketDto.getLocationDtos()));

        return ticket;
    }

    private List<Location> extractLocationDtos(List<LocationDto> locationDtos) {
        List<Location> locations = new ArrayList<>();
        for (LocationDto locationDto : locationDtos) {
            Location location = new Location();
            location.setRow(locationDto.getRow());
            location.setSeat(locationDto.getSeat());

            locations.add(location);
        }

        return locations;
    }

}
