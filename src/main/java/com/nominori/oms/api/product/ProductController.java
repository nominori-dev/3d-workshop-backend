package com.nominori.oms.api.product;

import com.nominori.oms.api.product.converter.ProductConverter;
import com.nominori.oms.api.product.dto.ProductDto;
import com.nominori.oms.api.product.model.ProductModel;
import com.nominori.oms.api.product.model.ProductModelAssembler;
import com.nominori.oms.application.product.ProductQueryService;
import com.nominori.oms.application.product.ProductService;
import com.nominori.oms.application.product.QueryProductParam;
import com.nominori.oms.core.product.Product;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
