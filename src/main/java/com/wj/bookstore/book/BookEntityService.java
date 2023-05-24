package com.wj.bookstore.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Service
public class BookEntityService {

    private final BookEntityRepository bookEntityRepository;

    @Autowired
    public BookEntityService(BookEntityRepository bookEntityRepository){
        this.bookEntityRepository = bookEntityRepository;
    }

    public ResponseEntity<Flux<BookEntity>> getAllBooks(){
        Flux<BookEntity> bookStream = bookEntityRepository.findAll();
        return new ResponseEntity<>(bookStream, HttpStatus.OK);
    }

    public Mono<ResponseEntity<?>> getBooksByAuthor(String author) {
        return bookEntityRepository.findBookEntitiesByAuthor(author)
                .collectList()
                .flatMap(books -> {
                    if (books.isEmpty()) {
                        return Mono.just(new ResponseEntity<>("There are no books with the provided author", HttpStatus.NOT_FOUND));
                    } else {
                        return Mono.just(new ResponseEntity<>(books, HttpStatus.OK));
                    }
                });
    }

    public Mono<ResponseEntity<?>> getBooksByTitle(String title) {
        return bookEntityRepository.findBookEntitiesByTitle(title)
                .collectList()
                .flatMap(books -> {
                    if (books.isEmpty()) {
                        return Mono.just(new ResponseEntity<>("There are no books with the provided title", HttpStatus.NOT_FOUND));
                    } else {
                        return Mono.just(new ResponseEntity<>(books, HttpStatus.OK));
                    }
                });
    }

    public Mono<ResponseEntity<?>> getBooksByDate(int year) {
        return bookEntityRepository.findBookEntitiesByReleaseDateBetween(
                        LocalDate.of(year, 1 ,1),
                        LocalDate.of(year, 12 ,31)
                )
                .collectList()
                .flatMap(books -> {
                    if (books.isEmpty()) {
                        return Mono.just(new ResponseEntity<>("There are no books with the provided year", HttpStatus.NOT_FOUND));
                    } else {
                        return Mono.just(new ResponseEntity<>(books, HttpStatus.OK));
                    }
                });
    }
}
