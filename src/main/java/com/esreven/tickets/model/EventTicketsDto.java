package com.esreven.tickets.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Data
@Builder
@Validated
public class EventTicketsDto extends Dto {

}
