package com.nominori.oms.user.common.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "oms_users")
@Getter
@Setter
@RequiredArgsConstructor
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
}
