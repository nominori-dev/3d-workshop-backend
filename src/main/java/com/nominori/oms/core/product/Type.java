package com.nominori.oms.core.product;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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


    public Type(String name) {
        this.name = name;
    }

    public Type(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Type update(String name, String description){
        this.name = name;
        this.description = description;

        return this;
    }
}
