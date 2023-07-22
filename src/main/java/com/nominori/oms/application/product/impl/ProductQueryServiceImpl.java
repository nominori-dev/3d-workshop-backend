package com.nominori.oms.application.product.impl;

import com.nominori.oms.api.exception.ResourceNotFoundException;
import com.nominori.oms.application.product.ProductQueryService;
import com.nominori.oms.core.product.Product;
import com.nominori.oms.core.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductQueryServiceImpl implements ProductQueryService {

    private final ProductRepository productRepository;

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product with provided ID not found."));
    }

    @Override
    public Product findByName(String name) {
        return productRepository.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("Product with provided name not found."));
    }
}
