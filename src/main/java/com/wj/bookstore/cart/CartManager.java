package com.wj.bookstore.cart;

import com.wj.bookstore.book.BookEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartManager {

    private List<BookEntity> cart = new ArrayList<>();

    public List<BookEntity> getItems(){
        return cart;
    }

    public void addItem(BookEntity book){
        cart.add(book);
    }

    public void removeItem(BookEntity book){
        cart.remove(book);
    }

    public void clear(){
        cart = new ArrayList<>();
    }

    public boolean isEmpty(){
        return cart.isEmpty();
    }

    public Optional<BookEntity> getBookById(String bookId){
        return cart.stream()
                .filter(el -> el.getId().equals(bookId))
                .findFirst();
    }
}
