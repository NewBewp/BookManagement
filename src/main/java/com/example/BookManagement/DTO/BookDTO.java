package com.example.BookManagement.DTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class BookDTO extends BaseDTO{
    private String title;
    private String author;
    private Integer quantity;
    private Integer quantitySold;
    private Float price;
}
