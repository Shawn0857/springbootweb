package com.example.test.controller;

import com.example.test.server.BorrowingService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/borrow")

public class BorrowingController {

    private final BorrowingService borrowingService;

    public BorrowingController(BorrowingService borrowingService) {
        this.borrowingService = borrowingService;
    }

    // 借書 API
    @PostMapping("/borrow")
    public String borrowBook(@RequestParam Long userId, @RequestParam Long inventoryId) {
        borrowingService.borrowBook(userId, inventoryId);
        return "借書成功";
    }

    // 還書 API
    @PostMapping("/return")
    public String returnBook(@RequestParam Long inventoryId) {
        borrowingService.returnBook(inventoryId);
        return "還書成功";
    }
}
