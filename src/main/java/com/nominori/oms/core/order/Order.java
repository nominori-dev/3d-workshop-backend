package com.nominori.oms.core.order;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.InstantSerializer;
import com.nominori.oms.core.user.common.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "oms_order")
@Getter
@Setter
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "email")
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private OrderStatus status = OrderStatus.CREATED;

    @Column(name = "total_price")
    private BigDecimal totalPrice = BigDecimal.ZERO;

    @CreationTimestamp
    @Column(name = "created_on")
    @JsonSerialize(using = InstantSerializer.class)
    private Instant createdOn;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "oms_order_items",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "order_item_id")
    )
    private List<OrderItem> orderItems = new ArrayList<>();

    public Order(String email) {
        this.email = email;
    }

    public void addOrderItem(OrderItem item){
        orderItems.add(item);
    }

    public void cancelOrder(){
        this.status = OrderStatus.CANCELLED;
    }

    public void completeOrder(){
        this.status = OrderStatus.COMPLETED;
    }

    public void calculateTotalPrice(){
        for (OrderItem item : this.orderItems) {
            BigDecimal itemPrice = item.getProduct()
                    .getPrice()
                    .multiply(BigDecimal.valueOf(item.getQuantity()));

            this.totalPrice = this.totalPrice.add(itemPrice);
        }
    }

}
