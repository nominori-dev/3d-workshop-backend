package com.nominori.oms.api.product;

import com.nominori.oms.api.product.converter.TypeConverter;
import com.nominori.oms.api.product.dto.TypeDto;
import com.nominori.oms.application.product.type.TypeParam;
import com.nominori.oms.application.product.type.TypeQueryService;
import com.nominori.oms.application.product.type.TypeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/type/")
public class TypeController {

    private final TypeService typeService;
    private final TypeQueryService typeQueryService;
    private final TypeConverter converter;

    @PostMapping
    public TypeDto addNewType(@RequestBody @Valid TypeDto dto){
        return converter.toDto(typeService.addType(converter.toEntity(dto)));
    }

    @GetMapping("{id}")
    public TypeDto getTypeById(@PathVariable Long id){
        return converter.toDto(typeQueryService.findById(id));
    }

    @PutMapping("{id}")
    public TypeDto updateTypeById(@PathVariable Long id, @RequestBody @Valid TypeDto dto){
        TypeParam param = converter.dtoToParam(dto);
        return converter.toDto(typeService.updateType(id, param));
    }

}
