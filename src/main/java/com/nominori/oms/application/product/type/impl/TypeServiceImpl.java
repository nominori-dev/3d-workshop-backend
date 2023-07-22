package com.nominori.oms.application.product.type.impl;

import com.nominori.oms.application.product.type.NewTypeParam;
import com.nominori.oms.application.product.type.TypeService;
import com.nominori.oms.core.product.Type;
import com.nominori.oms.core.product.TypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TypeServiceImpl implements TypeService {

    private final TypeRepository typeRepository;

    @Override
    public Type addType(NewTypeParam param) {
        return typeRepository.save(new Type(param.getName(), param.getDescription()));
    }

    @Override
    public Type updateType(Long id) {
        return null;
    }

    @Override
    public void removeType(Long id) {

    }
}
