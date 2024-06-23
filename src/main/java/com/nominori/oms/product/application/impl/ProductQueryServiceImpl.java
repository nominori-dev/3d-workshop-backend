package com.nominori.oms.product.application.impl;

import com.nominori.oms.shared.api.exception.ResourceNotFoundException;
import com.nominori.oms.product.application.ProductQueryService;
import com.nominori.oms.product.application.QueryProductParam;
import com.nominori.oms.product.application.type.TypeQueryService;
import com.nominori.oms.product.domain.Product;
import com.nominori.oms.product.repository.ProductRepository;
import com.nominori.oms.product.domain.Type;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ProductQueryServiceImpl implements ProductQueryService {

    private final ProductRepository productRepository;
    private final TypeQueryService typeQueryService;

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

    @Override
    public Page<Product> findAll(QueryProductParam param) {
        if(!param.getTypes().isEmpty()){
            Set<Type> typeSet = new HashSet<>();
            for (String type : param.getTypes()) {
                typeSet.add(typeQueryService.findByName(type));
            }
            return productRepository.findAllByProductTypeIn(typeSet, param
                    .getPageable(createSortOrder(param.getSortList(), param.getSortOrder())));
        }

        return productRepository.findAll(param
                .getPageable(createSortOrder(param.getSortList(), param.getSortOrder())));
    }

    private List<Sort.Order> createSortOrder(List<String> sortList, Sort.Direction sortDirection){
        List<Sort.Order> sorts = new ArrayList<>();
        sortList.forEach(sort -> sorts.add(new Sort.Order(sortDirection, sort)));

        return sorts;
    }

}
