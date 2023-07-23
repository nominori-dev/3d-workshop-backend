package com.nominori.oms.api.product.converter;

import com.nominori.oms.api.product.dto.TypeDto;
import com.nominori.oms.application.product.type.TypeParam;
import com.nominori.oms.core.product.Type;
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
