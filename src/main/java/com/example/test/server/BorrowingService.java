package com.example.test.server;
import com.example.test.demos.web.entity.BorrowingRecord;
import com.example.test.demos.web.entity.Inventory;
import com.example.test.repository.BorrowingRecordRepository;
import com.example.test.repository.InventoryRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
@Transactional
public class BorrowingService {

    private final InventoryRepository inventoryRepository;
    private final BorrowingRecordRepository recordRepository;

    public BorrowingService(InventoryRepository inventoryRepository, BorrowingRecordRepository recordRepository) {
        this.inventoryRepository = inventoryRepository;
        this.recordRepository = recordRepository;
    }

    public void borrowBook(Long userId, Long inventoryId) {
        Inventory inventory = inventoryRepository.findById(inventoryId)
                .orElseThrow(() -> new RuntimeException("書籍不在"));

        if (inventory.getStatus() != Inventory.Status.AVAILABLE) {
            throw new RuntimeException("書籍不可借");
        }

        // 更新庫存狀態
        inventory.setStatus(Inventory.Status.BORROWED);
        inventoryRepository.save(inventory);

        // 新增借閱紀錄
        BorrowingRecord record = new BorrowingRecord();
        record.setUserId(userId);
        record.setInventoryId(inventoryId);
        recordRepository.save(record);
    }

    // 還書
    public void returnBook(Long inventoryId) {
        Inventory inventory = inventoryRepository.findById(inventoryId)
                .orElseThrow(() -> new RuntimeException("書籍不在"));

        // 更新狀態
        inventory.setStatus(Inventory.Status.AVAILABLE);
        inventoryRepository.save(inventory);

        // 更新紀錄
        BorrowingRecord record = recordRepository.findByInventoryIdAndReturnTimeIsNull(inventoryId)
                .orElseThrow(() -> new RuntimeException("找不到借的紀錄"));
        record.setReturnTime(LocalDateTime.now());
        recordRepository.save(record);
    }
}
