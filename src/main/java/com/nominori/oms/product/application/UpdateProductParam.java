package com.nominori.oms.product.application;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProductParam {

    private String name = "";
    private String description = "";

}
