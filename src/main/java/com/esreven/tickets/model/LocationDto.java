package com.esreven.tickets.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Data
@Builder
@Validated
public class LocationDto {

    @JsonProperty("row")
    @NotNull
    private String row;

    @JsonProperty("seat")
    @NotNull
    private String seat;
}
