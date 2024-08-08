package com.example.BookManagement.DTO;

import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalDate;

@Data
public class BaseDTO {
    private Long id;

    private LocalDate createdAt;

    private LocalDate updatedAt;
}
