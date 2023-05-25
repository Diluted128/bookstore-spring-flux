package com.wj.bookstore.cart;

import com.wj.bookstore.book.BookEntity;
import com.wj.bookstore.book.BookEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    private final BookEntityRepository bookEntityRepository;
    private final CartManager cartManager;

    @Autowired
    public CartService(BookEntityRepository bookEntityRepository, CartManager cartManager) {
        this.bookEntityRepository = bookEntityRepository;
        this.cartManager = cartManager;
    }

    public ResponseEntity<Flux<BookEntity>> getAllBooksInCart(){
        List<BookEntity> books = cartManager.getItems();
        return new ResponseEntity<>(Flux.fromIterable(books), HttpStatus.ACCEPTED);
    }

    public Mono<ResponseEntity<String>> addBookToCart(String bookId){
        return bookEntityRepository.findById(bookId)
                .flatMap(book -> {

                    Optional<BookEntity> bookInCart = cartManager.getBookById(bookId);

                    if (bookInCart.isPresent()) {
                        return Mono.just(new ResponseEntity<>("Book " + book.getId() + " is already in cart.", HttpStatus.CONFLICT));
                    } else {
                        cartManager.addItem(book);
                        return Mono.just(new ResponseEntity<>("Book " + bookId + " has been added to cart", HttpStatus.ACCEPTED));
                    }
                })
                .switchIfEmpty(Mono.just(new ResponseEntity<>("Book " + bookId + " wasn't found", HttpStatus.NOT_FOUND)));
    }

    public Mono<ResponseEntity<String>> removeBookFromCart(String bookId) {
        return bookEntityRepository.findById(bookId)
                .flatMap(book -> {

                    Optional<BookEntity> bookToRemove = cartManager.getBookById(bookId);

                    if (bookToRemove.isPresent()) {
                        cartManager.removeItem(bookToRemove.get());
                        return Mono.just(new ResponseEntity<>("Book " + book.getId() + " has been removed from the cart", HttpStatus.ACCEPTED));
                    } else {
                        return Mono.just(new ResponseEntity<>("Book " + book.getId() + " hasn't been found in the cart", HttpStatus.NOT_FOUND));
                    }
                })
                .switchIfEmpty(Mono.just(new ResponseEntity<>("Book " + bookId + " wasn't found", HttpStatus.NOT_FOUND)));
    }
}
