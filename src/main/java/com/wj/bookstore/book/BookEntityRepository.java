package com.wj.bookstore.book;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.time.LocalDate;

@Repository
public interface BookEntityRepository extends ReactiveMongoRepository<BookEntity, String> {

    Flux<BookEntity> findBookEntitiesByAuthor(String author);
    Flux<BookEntity> findBookEntitiesByTitle(String title);
    Flux<BookEntity> findBookEntitiesByReleaseDateBetween(LocalDate startDate, LocalDate endDate);
}
