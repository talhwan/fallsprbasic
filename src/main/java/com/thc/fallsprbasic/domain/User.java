package com.thc.fallsprbasic.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Integer id;

    @Column(unique=true, nullable=false)
    String username;

    @Column(nullable=false)
    String password;

    String name;
    String phone;
}
