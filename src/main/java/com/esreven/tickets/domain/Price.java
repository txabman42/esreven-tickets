package com.esreven.tickets.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Price {

    private static final long serialVersionUID = -1L;

    private Long amount;
    private String currency;
}
