package com.nominori.oms.product.application.type.impl;

import com.nominori.oms.shared.api.exception.ResourceAlreadyExistsException;
import com.nominori.oms.product.application.type.TypeParam;
import com.nominori.oms.product.application.type.TypeQueryService;
import com.nominori.oms.product.application.type.TypeService;
import com.nominori.oms.product.domain.Type;
import com.nominori.oms.product.repository.TypeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class TypeServiceImpl implements TypeService {

    private final TypeRepository typeRepository;
    private final TypeQueryService typeQueryService;

    @Override
    public Type addType(Type type) {
        log.info("Adding new Type: " + type.getName());

        if(typeRepository.existsByName(type.getName())){
            throw new ResourceAlreadyExistsException("Type with provided name already exists");
        }

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
