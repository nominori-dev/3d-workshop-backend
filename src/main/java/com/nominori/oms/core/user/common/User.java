package com.nominori.oms.core.user.common;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "oms_users")
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "email")
    private String email;

    @CreationTimestamp
    @Column(name = "created_on")
    private Instant createdOn;

    public User(UUID id, String email) {
        this.id = id;
        this.email = email;
    }
}
