package com.esreven.tickets.domain;

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
@Document(indexName = "ticket", type = "ticket")
public class Ticket {

    @Id
    private Long listingId;

    @Field(type = FieldType.Long)
    private Long sectionId;

    @Field(type = FieldType.Integer)
    private int quantity;

    @Field(type = FieldType.Keyword)
    private String sellerSectionName;

    @Field(type = FieldType.Keyword)
    private String sectionName;

    @Field(type = FieldType.Long)
    private Long zoneId;

    @Field(type = FieldType.Object)
    private List<Integer> deliveryTypeList;

    @Field(type = FieldType.Boolean)
    private boolean isGA;

    @Field(type = FieldType.Keyword)
    private String splitOption;

    @Field(type = FieldType.Long)
    private Long sellerOwnInd;

    @Field(type = FieldType.Object)
    private Price faceValue;

    @Field(type = FieldType.Boolean)
    private boolean isSectionMapped;

    @Field(type = FieldType.Object)
    private Price pricePerProduct;

    @Field(type = FieldType.Keyword)
    private String zone;

    @Field(type = FieldType.Keyword)
    private String splitQuantity;

    @Field(type = FieldType.Object)
    private List<Integer> buyQuantityOptions;

    @Field(type = FieldType.Object)
    private List<Location> products;

}
