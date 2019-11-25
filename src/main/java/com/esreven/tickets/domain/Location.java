package com.esreven.tickets.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Location {

    private static final long serialVersionUID = -1L;

    private String row;
    private String seat;
}
