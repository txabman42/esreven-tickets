package com.esreven.tickets.client;

import com.esreven.tickets.model.EventTicketDto;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

public class EventClientImp implements EventClient {

    @Value(value = "${rest.token}")
    private String token;

    @Value(value = "${rest.ticketsPerEvent.uri}")
    private String eventTicketUri;

    @Override
    public EventTicketDto getTicketsPerEvent(String eventId) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set("Authorization", "Bearer " + token);

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(eventTicketUri)
            .queryParam("eventId", eventId)
            .queryParam("start", 0)
            .queryParam("rows", 10);

        EventTicketDto eventTicketDto = restTemplate.postForObject(
            uriBuilder.toUriString(),

            EventTicketDto.class
        );
    }
}
