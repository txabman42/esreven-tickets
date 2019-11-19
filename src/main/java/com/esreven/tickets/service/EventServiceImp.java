package com.esreven.tickets.service;

import com.esreven.tickets.domain.Event;
import com.esreven.tickets.domain.Location;
import com.esreven.tickets.domain.Price;
import com.esreven.tickets.domain.Ticket;
import com.esreven.tickets.model.EventDto;
import com.esreven.tickets.model.LocationDto;
import com.esreven.tickets.model.PriceDto;
import com.esreven.tickets.model.TicketDto;
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
public class EventServiceImp implements EventService {

    private static final String INDEX = "events";

    private RestHighLevelClient client;
    private ObjectMapper objectMapper;

    public EventServiceImp(RestHighLevelClient client, ObjectMapper objectMapper) {
        this.client = client;
        this.objectMapper = objectMapper;
    }

    @Override
    public EventDto save(EventDto eventDto) throws IOException {
        Event event = new Event();
        event.setId(UUID.randomUUID());
        event.setEventId(eventDto.getEventId());
        event.setTotalListing(eventDto.getTotalListings());
        event.setTotalTickets(eventDto.getTotalTickets());
        event.setMinQuantity(eventDto.getMinQuantity());
        event.setMaxQuantity(eventDto.getMaxQuantity());
        event.setStart(eventDto.getStart());
        event.setRows(eventDto.getRows());
        event.setListings(extractTicketDtos(eventDto.getListings()));

        String eventString = objectMapper.writeValueAsString(event);

        IndexRequest request = new IndexRequest(INDEX);
        request.id(event.getEventId().toString());
        request.source(eventString, XContentType.JSON);

        IndexResponse response = client.index(request, RequestOptions.DEFAULT);

        if (response.status() == RestStatus.CREATED) {
            log.info("Index request {} for {} event successfully created", request.id(), event.getId());
        }

        return assembleEvent(event);
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
            .listings(event.getListings().stream().map(
                p -> assembleTicket(p))
                .collect(Collectors.toList()))
            .build();
    }

    private TicketDto assembleTicket(Ticket ticket) {
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
            .faceValue(PriceDto.builder()
                .currency(ticket.getFaceValue().getCurrency())
                .build())
            .isSectionMapped(ticket.getIsSectionMapped())
            .pricePerProduct(PriceDto.builder()
                .amount(ticket.getPricePerProduct().getAmount())
                .currency(ticket.getPricePerProduct().getCurrency())
                .build())
            .zone(ticket.getZone())
            .splitQuantity(ticket.getSplitQuantity())
            .buyQuantityOptions(ticket.getBuyQuantityOptions())
            .products(ticket.getProducts().stream().map(
                q -> LocationDto.builder()
                    .row(q.getRow())
                    .seat(q.getSeat())
                    .build()
            ).collect(Collectors.toList()))
            .build();
    }

    private List<Ticket> extractTicketDtos(List<TicketDto> ticketDtos) {
        List<Ticket> tickets = new ArrayList<>();
        for (TicketDto ticketDto : ticketDtos) {
            Ticket ticket = new Ticket();
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
            ticket.setFaceValue(new Price(ticketDto.getFaceValue().getAmount(),
                ticketDto.getFaceValue().getCurrency()));
            ticket.setIsSectionMapped(ticketDto.getIsSectionMapped());
            ticket.setPricePerProduct(new Price(ticketDto.getPricePerProduct().getAmount(),
                ticketDto.getPricePerProduct().getCurrency()));
            ticket.setZone(ticketDto.getZone());
            ticket.setSplitQuantity(ticketDto.getSplitQuantity());
            ticket.setBuyQuantityOptions(ticketDto.getBuyQuantityOptions());
            ticket.setProducts(extractLocationDtos(ticketDto.getProducts()));

            tickets.add(ticket);
        }

        return tickets;
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
