package com.nominori.oms.product.application.type.impl;

import com.nominori.oms.shared.api.exception.ResourceNotFoundException;
import com.nominori.oms.product.application.type.TypeQueryService;
import com.nominori.oms.product.domain.Type;
import com.nominori.oms.product.repository.TypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TypeQueryServiceImpl implements TypeQueryService {

    private final TypeRepository typeRepository;

    @Override
    public Type findById(Long id) {
        return typeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Type not found by provided ID."));
    }

    @Override
    public Type findByName(String name) {
        return typeRepository.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("Type not found by provided name."));
    }
}
