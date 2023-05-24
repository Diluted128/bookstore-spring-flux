package com.wj.bookstore.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/books")
public class BookEntityController {

    private final BookEntityService bookEntityService;

    @Autowired
    public BookEntityController(BookEntityService bookEntityService){
        this.bookEntityService = bookEntityService;
    }

    @GetMapping
    public ResponseEntity<Flux<BookEntity>> getAllBooks(){
       return bookEntityService.getAllBooks();
    }

    @GetMapping("/author/{author}")
    public Mono<ResponseEntity<?>> getBooksByAuthor(@PathVariable String author){
        return bookEntityService.getBooksByAuthor(author);
    }

    @GetMapping("/title/{title}")
    public Mono<ResponseEntity<?>> getBooksByTitle(@PathVariable String title){
        return bookEntityService.getBooksByTitle(title);
    }

    @GetMapping("/date/{date}")
    public Mono<ResponseEntity<?>> getBooksByYear(@PathVariable int date){
        return bookEntityService.getBooksByDate(date);
    }
}
