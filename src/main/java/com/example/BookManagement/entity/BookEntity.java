package com.example.BookManagement.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "Books")
public class BookEntity extends BaseEntity{
    private String title;
    private String author;
    @Column(nullable = false)
    private Integer quantity;
    @Column(nullable = false)
    private Integer quantitySold;
    private Float price;
}
