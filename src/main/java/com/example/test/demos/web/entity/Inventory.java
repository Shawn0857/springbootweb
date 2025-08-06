package com.example.test.demos.web.entity;


import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "inventory")
@Data
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inventoryId;

    @Column(nullable = false)
    private String isbn;

    private LocalDateTime storeTime = LocalDateTime.now();

    @Enumerated(EnumType.STRING) //規定字串狀態
    private Status status = Status.AVAILABLE;

    public enum Status {
        AVAILABLE, BORROWED, MAINTENANCE, LOST, DISCARDED
    }
}
