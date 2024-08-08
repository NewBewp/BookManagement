package com.example.BookManagement.repository;

import com.example.BookManagement.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepo extends JpaRepository<BookEntity, Long> {
    Iterable<BookEntity> findAllByOrderByPriceAsc();
    Iterable<BookEntity> findAllByOrderByQuantitySoldDesc();
    List<BookEntity> findByTitleContainingIgnoreCase(String keyword);
    List<BookEntity> findByCreatedAtBetween(LocalDate startDate, LocalDate endDate);
    List<BookEntity> findTop5ByOrderByQuantitySoldDesc();
}
