package com.example.test.repository;

import com.example.test.demos.web.entity.Inventory;
import com.example.test.demos.web.entity.BorrowingRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BorrowingRecordRepository extends JpaRepository<BorrowingRecord, Long> {

    // 找出指定 inventoryId 且尚未還書的紀錄
    Optional<BorrowingRecord> findByInventoryIdAndReturnTimeIsNull(Long inventoryId);
}
