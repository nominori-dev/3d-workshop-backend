package com.nominori.oms.application.product;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
public class QueryProductParam {

    private int page = 0;
    private int size = 5;

    private List<String> sortList = List.of("name");
    private Sort.Direction sortOrder = Sort.Direction.DESC;
    private List<String> types = new ArrayList<>();

    public Pageable getPageable(){
        return PageRequest.of(this.page, this.size);
    }

    public Pageable getPageable(List<Sort.Order> sortOrder){
        return PageRequest.of(this.page, this.size, Sort.by(sortOrder));
    }

}
