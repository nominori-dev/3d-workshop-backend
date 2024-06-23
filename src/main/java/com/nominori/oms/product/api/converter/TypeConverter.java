package com.nominori.oms.product.api.converter;

import com.nominori.oms.product.api.dto.TypeDto;
import com.nominori.oms.product.application.type.TypeParam;
import com.nominori.oms.product.domain.Type;
import org.springframework.stereotype.Component;

@Component
public class TypeConverter {

    public Type toEntity(TypeDto dto){
        return new Type(dto.getName(), dto.getDescription());
    }

    public TypeDto toDto(Type type){
        return new TypeDto(type.getId(), type.getName(), type.getDescription());
    }

    public TypeParam dtoToParam(TypeDto dto){
        return new TypeParam(dto.getName(), dto.getDescription());
    }

}
