package com.esreven.tickets.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Data
@Builder
@Validated
public class TicketDto {

    @JsonProperty("listingId")
    @NotNull
    private Long listingId;

    @JsonProperty("sectionId")
    private Long sectionId;

    @JsonProperty("quantity")
    @NotNull
    private Integer quantity;

    @JsonProperty("sellerSectionName")
    @NotNull
    private String sellerSectionName;

    @JsonProperty("sectionName")
    @NotNull
    private String sectionName;

    @JsonProperty("zoneId")
    @NotNull
    private Long zoneId;

    @JsonProperty("deliveryTypeList")
    @NotNull
    private List<Integer> deliveryTypeList;

    @JsonProperty("isGA")
    @NotNull
    private Boolean isGA;

    @JsonProperty("splitOption")
    @NotNull
    private String splitOption;

    @JsonProperty("sellerOwnInd")
    @NotNull
    private Long sellerOwnInd;

    @JsonProperty("faceValue")
    @NotNull
    private PriceDto faceValue;

    @JsonProperty("isSectionMapped")
    @NotNull
    private Boolean isSectionMapped;

    @JsonProperty("pricePerProduct")
    @NotNull
    private PriceDto pricePerProduct;

    @JsonProperty("zone")
    @NotNull
    private String zone;

    @JsonProperty("splitQuantity")
    @NotNull
    private String splitQuantity;

    @JsonProperty("buyQuantityOptions")
    @NotNull
    private List<Integer> buyQuantityOptions;

    @JsonProperty("products")
    @NotNull
    private List<LocationDto> products;
}
