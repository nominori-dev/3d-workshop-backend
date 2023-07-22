package com.nominori.oms.api.product.dto;

import com.nominori.oms.core.product.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
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
