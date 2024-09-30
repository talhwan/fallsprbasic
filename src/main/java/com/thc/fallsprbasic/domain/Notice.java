package com.thc.fallsprbasic.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
public class Notice {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id Long id;

    @Setter @Column(nullable=false)
    String title;
    @Setter
    String content;
}
