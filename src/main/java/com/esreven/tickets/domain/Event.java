package com.esreven.tickets.domain;

import static org.springframework.data.elasticsearch.annotations.FieldType.Nested;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "event", type = "event")
public class Event {

    @Id
    private Long eventId;

    @Field(type = FieldType.Long)
    private Long totalListing;

    @Field(type = FieldType.Long)
    private Long totalTickets;

    @Field(type = FieldType.Integer)
    private int minQuantity;

    @Field(type = FieldType.Integer)
    private int maxQuantity;

    @Field(type = FieldType.Integer)
    private int start;

    @Field(type = FieldType.Integer)
    private int rows;

    @Field(type = Nested, includeInParent = true)
    private List<Ticket> listings;

}
