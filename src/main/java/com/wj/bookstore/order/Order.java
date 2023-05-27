package com.wj.bookstore.order;

import com.wj.bookstore.book.BookEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Document
@Setter
@Getter
@AllArgsConstructor
public class Order {
    @Id
    private String orderId;
    private String username;
    private List<BookEntity> books;
    private LocalDate orderDate;
}
