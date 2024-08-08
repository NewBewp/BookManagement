package com.example.BookManagement.service;

import com.example.BookManagement.entity.BookEntity;
import com.example.BookManagement.repository.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

public interface BookService extends BaseService<BookEntity>{
    Iterable<BookEntity> sortByPrice();
    Iterable<BookEntity> sortByQuantitySold();
    BookEntity sellBook(Long id,Integer numbSold);
    List<BookEntity> searchByIdOrTitle(String keyword);
    List<BookEntity> findByCreatedAtBetween(LocalDate startDate, LocalDate endDate);
    List<BookEntity> findTop5ByOrderByQuantitySoldDesc();
}
