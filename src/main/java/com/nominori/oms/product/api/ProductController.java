package com.nominori.oms.product.api;

import com.nominori.oms.product.api.converter.ProductConverter;
import com.nominori.oms.product.api.dto.ProductDto;
import com.nominori.oms.product.api.model.ProductModel;
import com.nominori.oms.product.api.model.ProductModelAssembler;
import com.nominori.oms.product.application.ProductQueryService;
import com.nominori.oms.product.application.ProductService;
import com.nominori.oms.product.application.QueryProductParam;
import com.nominori.oms.product.domain.Product;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product/")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ProductQueryService productQueryService;
    private final ProductConverter productConverter;
    private final ProductModelAssembler productModelAssembler;
    private final PagedResourcesAssembler<Product> productPagedResourcesAssembler;

    @PostMapping
    public Product addNewProduct(@RequestBody @Valid ProductDto productDto){
        return productService.addProduct(productConverter.toEntity(productDto));
    }

    @GetMapping("{id}")
    public Product getProductById(@PathVariable Long id){
        return productQueryService.findById(id);
    }

    @GetMapping
    public PagedModel<ProductModel> getAllProducts(QueryProductParam param){
        Page<Product> productPage = productQueryService.findAll(param);
        return productPagedResourcesAssembler.toModel(productPage, productModelAssembler);
    }

}
