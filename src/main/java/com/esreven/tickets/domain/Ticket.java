package com.esreven.tickets.domain;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {

    private Long id;
    private Long listingId;
    private Event event;
    private Long sectionId;
    private Integer quantity;
    private String sellerSectionName;
    private String sectionName;
    private Long zoneId;
    private List<Integer> deliveryTypeList;
    private Boolean isGA;
    private String splitOption;
    private Long sellerOwnInd;
    private Price faceValue;
    private Boolean isSectionMapped;
    private Price pricePerProduct;
    private String zone;
    private String splitQuantity;
    private List<Integer> buyQuantityOptions;

}
