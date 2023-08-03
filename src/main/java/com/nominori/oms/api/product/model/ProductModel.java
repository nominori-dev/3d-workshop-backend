package com.nominori.oms.api.product.model;

import com.nominori.oms.core.product.Type;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
public class ProductModel extends RepresentationModel<ProductModel> {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Set<Type> productType;
}
