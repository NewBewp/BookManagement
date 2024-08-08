package com.example.BookManagement.service.impl;

import com.example.BookManagement.entity.BookEntity;
import com.example.BookManagement.repository.BookRepo;
import com.example.BookManagement.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepo repo;


    @Override
    public BookEntity save(BookEntity bookEntity) {
        return repo.save(bookEntity);
    }

    @Override
    public Optional<BookEntity> findById(Long id) {
        return repo.findById(id);
    }

    @Override
    public Iterable<BookEntity> findAll() {
        return repo.findAll();
    }

    @Override
    public void deleteById(Long id) {
        repo.deleteById(id);
    }

    @Override
    public Iterable<BookEntity> sortByPrice() {
        return repo.findAllByOrderByPriceAsc();
    }

    @Override
    public Iterable<BookEntity> sortByQuantitySold() {
        return repo.findAllByOrderByQuantitySoldDesc();
    }

    @Override
    public BookEntity sellBook(Long id, Integer numbSold) {
        Optional<BookEntity> optionalBook = repo.findById(id);
        if (optionalBook.isPresent() && optionalBook.get().getQuantity() >= numbSold) {
            BookEntity bookEntity = optionalBook.get();
            bookEntity.setQuantitySold(bookEntity.getQuantitySold() + numbSold);
            bookEntity.setQuantity(bookEntity.getQuantity() - numbSold);
            return repo.save(bookEntity);
        } else {
            throw new RuntimeException("Book not found with id: " + id);
        }
    }

    @Override
    public List<BookEntity> searchByIdOrTitle(String keyword) {

        try {
            Long id = Long.parseLong(keyword);
            Optional<BookEntity> bookEntity = repo.findById(id);

            return bookEntity.map(List::of).orElse(List.of());

        } catch (NumberFormatException e) {
            return repo.findByTitleContainingIgnoreCase(keyword);
        }
    }

    @Override
    public List<BookEntity> findByCreatedAtBetween(LocalDate startDate, LocalDate endDate) {
        return repo.findByCreatedAtBetween(startDate,endDate);
    }

    @Override
    public List<BookEntity> findTop5ByOrderByQuantitySoldDesc() {
        return repo.findTop5ByOrderByQuantitySoldDesc();
    }
}
