package com.nominori.oms.api.product;

import com.nominori.oms.api.product.converter.ProductConverter;
import com.nominori.oms.api.product.dto.ProductDto;
import com.nominori.oms.application.product.ProductQueryService;
import com.nominori.oms.application.product.ProductService;
import com.nominori.oms.core.product.Product;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product/")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ProductQueryService productQueryService;
    private final ProductConverter productConverter;

    @PostMapping
    @PreAuthorize("hasAnyRole({'API_ADMIN'})")
    public Product addNewProduct(@RequestBody @Valid ProductDto productDto){
        return productService.addProduct(productConverter.toEntity(productDto));
    }

    @GetMapping("{id}")
    public Product getProductById(@PathVariable Long id){
        return productQueryService.findById(id);
    }

}
