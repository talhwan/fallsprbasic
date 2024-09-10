package com.thc.fallsprbasic.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Board {
    @Id
    Integer id;

    String title;
    String content;
    String author;
}
