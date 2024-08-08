package com.example.BookManagement.controller;

import com.example.BookManagement.DTO.BookDTO;
import com.example.BookManagement.entity.BookEntity;
import com.example.BookManagement.service.BookService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping
    public Iterable<BookEntity> getAllBooks() {
        return bookService.findAll();
    }

    @PostMapping
    public BookEntity create(@RequestBody BookDTO bookDTO) {
        BookEntity bookEntity = new BookEntity();
        if (bookDTO.getTitle() == null) {
            throw new ArithmeticException();
        }
        bookEntity.setTitle(bookDTO.getTitle());
        bookEntity.setAuthor(bookDTO.getAuthor());
        bookEntity.setQuantity(bookDTO.getQuantity());
        bookEntity.setQuantitySold(bookDTO.getQuantitySold());
        bookEntity.setPrice(bookDTO.getPrice());
        bookEntity.setCreatedAt(LocalDate.now());
        return bookService.save(bookEntity);
    }

    @PutMapping
    public BookEntity update(@RequestBody BookDTO bookDTO) {
        BookEntity bookEntity = new BookEntity();
        if (bookDTO.getTitle() == null) {
            throw new ArithmeticException();
        }
        bookEntity.setId(bookDTO.getId());
        bookEntity.setTitle(bookDTO.getTitle());
        bookEntity.setAuthor(bookDTO.getAuthor());
        bookEntity.setQuantity(bookDTO.getQuantity());
        bookEntity.setQuantitySold(bookDTO.getQuantitySold());
        bookEntity.setPrice(bookDTO.getPrice());
        bookEntity.setUpdatedAt(LocalDate.now());
        return bookService.save(bookEntity);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        bookService.deleteById(id);
    }

    @GetMapping("/sortByPrice")
    public Iterable<BookEntity> sortByPrice() {
        return bookService.sortByPrice();
    }

    @GetMapping("/sortByQuantitySold")
    public Iterable<BookEntity> sortByQuantitySold() {
        return bookService.sortByQuantitySold();
    }

    @PutMapping("/sellBook")
    public ResponseEntity<BookEntity> sellBook(
            @PathParam("id") Long id,
            @PathParam("numbSold") Integer numbSold
    ) {
        try {
            BookEntity bookSold = bookService.sellBook(id,numbSold);
            return ResponseEntity.ok(bookSold);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/search")
    public List<BookEntity> searchByIdOrTitle(@RequestParam String keyword){
        List<BookEntity> books  = bookService.searchByIdOrTitle(keyword);
        if (books.isEmpty()) {
            throw new ArithmeticException();
        }
        return books;
    }

    @GetMapping("/searchByDate")
    public List<BookEntity> findByImportDateBetween(
            @RequestParam("createdAt") String startDate,
            @RequestParam("endDate") String endDate
    ){
        List<BookEntity> books = bookService.findByCreatedAtBetween(LocalDate.parse(startDate), LocalDate.parse(endDate));
        if (books.isEmpty()) {
            throw new ArithmeticException();
        }
        return books;
    }

    @GetMapping("/top5BestSellers")
    public List<BookEntity> getTop5BestSellers (){
        List<BookEntity> books = bookService.findTop5ByOrderByQuantitySoldDesc();
        if (books.isEmpty()) {
            throw new ArithmeticException();
        }
        return books;
    }
}
