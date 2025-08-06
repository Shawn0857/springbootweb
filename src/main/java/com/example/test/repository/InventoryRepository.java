package com.example.test.repository;

import com.example.test.demos.web.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    List<Inventory> findByStatus(Inventory.Status status);
    List<Inventory> findByIsbn(String isbn);
}
