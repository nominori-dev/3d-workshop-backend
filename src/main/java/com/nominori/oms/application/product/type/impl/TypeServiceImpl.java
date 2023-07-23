package com.nominori.oms.application.product.type.impl;

import com.nominori.oms.api.exception.ResourceNotFoundException;
import com.nominori.oms.application.product.type.TypeParam;
import com.nominori.oms.application.product.type.TypeQueryService;
import com.nominori.oms.application.product.type.TypeService;
import com.nominori.oms.core.product.Type;
import com.nominori.oms.core.product.TypeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class TypeServiceImpl implements TypeService {

    private final TypeRepository typeRepository;
    private final TypeQueryService typeQueryService;


    // #TODO Catch name constraint violation
    @Override
    public Type addType(Type type) {
        log.info("Adding new Type: " + type.getName());
        return typeRepository.save(type);
    }

    @Override
    public Type updateType(Long id, TypeParam param) {
        Type type = typeQueryService.findById(id);
        log.info("Updating Type: " + param.getName());
        return typeRepository.save(type.update(param.getName(), param.getDescription()));
    }

    @Override
    public void removeType(Long id) {
        Type type = typeQueryService.findById(id);
        log.info("Removing Type: " + type.getName());
        typeRepository.delete(type);
    }
}
