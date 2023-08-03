package com.nominori.oms.api.order.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderItemDto {

    @JsonProperty("product_id")
    private Long productId;
    private Long quantity;

}
