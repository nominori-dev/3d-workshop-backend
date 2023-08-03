package com.nominori.oms.core.product;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "oms_products")
@Getter
@Setter
@NoArgsConstructor
public class Product {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private BigDecimal price = BigDecimal.ZERO;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "oms_product_type",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "type_id")
    )
    private Set<Type> productType = new HashSet<>();

    public Product(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Product(String name, String description, Set<Type> productType) {
        this.name = name;
        this.description = description;
        this.productType = productType;
    }

    public void update(String name, String description){
        this.name = name;
        this.description = description;
    }
}
