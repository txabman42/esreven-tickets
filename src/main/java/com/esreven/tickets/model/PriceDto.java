package com.esreven.tickets.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Data
@Builder
@Validated
public class PriceDto {

    @JsonProperty("amount")
    @NotNull
    private Long amount;

    @JsonProperty("currency")
    @NotNull
    private String currency;
}
