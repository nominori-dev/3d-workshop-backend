package com.nominori.oms.api.product.converter;

import com.nominori.oms.api.exception.ResourceNotFoundException;
import com.nominori.oms.api.product.dto.ProductDto;
import com.nominori.oms.application.product.type.TypeQueryService;
import com.nominori.oms.application.product.type.TypeService;
import com.nominori.oms.core.product.Product;
import com.nominori.oms.core.product.Type;
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

}
