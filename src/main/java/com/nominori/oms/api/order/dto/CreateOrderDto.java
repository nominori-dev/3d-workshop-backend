package com.nominori.oms.api.order.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class CreateOrderDto {

    @NotBlank
    @Size(max = 355)
    private String email;

    @JsonProperty("order_items")
    private Set<OrderItemDto> orderItems;

}
