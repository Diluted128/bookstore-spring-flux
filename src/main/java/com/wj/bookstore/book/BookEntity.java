package com.wj.bookstore.book;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@Document(collection = "books")
public class BookEntity {
    @Id
    private String id;
    private String title;
    private String author;
    private LocalDate releaseDate;
    private Long ISBN;
}
