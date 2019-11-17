package com.esreven.tickets.repository;

import com.esreven.tickets.domain.Ticket;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends ElasticsearchRepository<Ticket, String> {

}
