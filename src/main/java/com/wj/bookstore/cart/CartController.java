package com.wj.bookstore.cart;

import com.wj.bookstore.book.BookEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService){
        this.cartService = cartService;
    }

    @GetMapping("/cart/books")
    public ResponseEntity<Flux<BookEntity>> getBooksInCart(){
        return cartService.getAllBooksInCart();
    }

    @PostMapping("/cart/books/{bookId}")
    public Mono<ResponseEntity<String>> addBookToCart(@PathVariable String bookId){
        return cartService.addBookToCart(bookId);
    }

    @DeleteMapping("/cart/books/{bookId}")
    public Mono<ResponseEntity<String>> removeBookFromCart(@PathVariable String bookId) {
        return cartService.removeBookFromCart(bookId);
    }
}
