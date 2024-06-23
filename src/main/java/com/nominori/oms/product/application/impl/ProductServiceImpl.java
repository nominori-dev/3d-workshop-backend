package com.nominori.oms.product.application.impl;

import com.nominori.oms.shared.api.exception.ResourceAlreadyExistsException;
import com.nominori.oms.product.application.ProductService;
import com.nominori.oms.product.application.UpdateProductParam;
import com.nominori.oms.product.domain.Product;
import com.nominori.oms.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Product addProduct(Product product) {
        if(productRepository.existsByName(product.getName())){
            throw new ResourceAlreadyExistsException("Product with provided name already exists.");
        }
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product, UpdateProductParam updateProductParam) {
        product.update(updateProductParam.getName(), updateProductParam.getDescription());
        return productRepository.save(product);
    }

    @Override
    public void removeProduct(Product product) {
        productRepository.delete(product);
    }
}
