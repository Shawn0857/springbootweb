package com.example.test.demos.web.entity;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "book")
@Data
public class Book {

    @Id
    private String isbn;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String author;

    @Column(columnDefinition = "TEXT")
    private String introduction;
}
