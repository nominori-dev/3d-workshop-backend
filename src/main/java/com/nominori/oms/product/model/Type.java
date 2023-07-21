package com.nominori.oms.product.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "oms_types")
@Getter
@Setter
@NoArgsConstructor
public class Type {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "type_name")
    private String name;

    @Column(name = "type_description")
    private String description;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "productType")
    private Set<Product> products = new HashSet<>();

}
