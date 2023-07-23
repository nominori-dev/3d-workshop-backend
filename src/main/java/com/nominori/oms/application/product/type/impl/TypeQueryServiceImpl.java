package com.nominori.oms.application.product.type.impl;

import com.nominori.oms.api.exception.ResourceNotFoundException;
import com.nominori.oms.application.product.type.TypeQueryService;
import com.nominori.oms.core.product.Type;
import com.nominori.oms.core.product.TypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TypeQueryServiceImpl implements TypeQueryService {

    private final TypeRepository typeRepository;

    @Override
    public Type findById(Long id) {
        return typeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Type not found by provided ID."));
    }
}
