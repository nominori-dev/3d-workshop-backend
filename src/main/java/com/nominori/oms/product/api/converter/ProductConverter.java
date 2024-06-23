package com.nominori.oms.product.api.converter;

import com.nominori.oms.product.api.dto.ProductDto;
import com.nominori.oms.product.application.type.TypeQueryService;
import com.nominori.oms.product.application.type.TypeService;
import com.nominori.oms.product.domain.Product;
import com.nominori.oms.product.domain.Type;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class ProductConverter {

    private final TypeService typeService;
    private final TypeQueryService typeQueryService;

    public Product toEntity(ProductDto dto){
        Product product = new Product(dto.getName(), dto.getDescription());
        Set<Type> productTypes = new HashSet<>();

        if(dto.getProductTypeIds() != null && !dto.getProductTypeIds().isEmpty()){
            dto.getProductTypeIds().forEach(id -> {
                Type type = typeQueryService.findById(id);
                if(type != null){
                    productTypes.add(type);
                }
            });
        }

        product.setProductType(productTypes);
        return product;
    }

    public ProductDto toDto(Product product){
        Set<Long> productTypeIds = new HashSet<>();
        product.getProductType().forEach(type -> {
            productTypeIds.add(product.getId());
        });

        return ProductDto.builder()
                .name(product.getName())
                .description(product.getDescription())
                .productTypeIds(productTypeIds).build();
    }

}
