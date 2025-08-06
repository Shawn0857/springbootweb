package com.example.test.demos.web.entity;

import lombok.Data;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "user")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false, unique = true)
    private String phoneNumber;

    @Column(nullable = false)
    private String password; // 會存 hash 後的密碼

    @Column(nullable = false)
    private String userName;

    private LocalDateTime registrationTime;

    private LocalDateTime lastLoginTime;

}
