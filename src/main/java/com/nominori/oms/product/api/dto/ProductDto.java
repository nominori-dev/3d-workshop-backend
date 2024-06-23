package com.nominori.oms.product.api.dto;

import com.nominori.oms.product.domain.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto {

    @NotBlank
    @Size(max = 355)
    private String name;

    @NotBlank
    @Size(max = 5000)
    private String description;
    private Set<Long> productTypeIds;

    @Deprecated(forRemoval = true)
    public Product toEntity(){
        return new Product(this.name, this.description);
    }



}
