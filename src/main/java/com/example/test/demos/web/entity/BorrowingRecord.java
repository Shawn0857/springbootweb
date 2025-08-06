package com.example.test.demos.web.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "borrowing_record")
@Data
public class BorrowingRecord {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recordId;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Long inventoryId;

    @Column(nullable = false)
    private LocalDateTime borrowingTime = LocalDateTime.now();

    private LocalDateTime returnTime;
}
