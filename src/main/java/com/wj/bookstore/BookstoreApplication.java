package com.wj.bookstore;

import com.wj.bookstore.book.BookEntity;
import com.wj.bookstore.book.BookEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Flux;

import javax.annotation.PostConstruct;
import java.time.LocalDate;

@SpringBootApplication
public class BookstoreApplication {

    @Autowired
    private BookEntityRepository bookEntityRepository;

    public static void main(String[] args) {
        SpringApplication.run(BookstoreApplication.class, args);
    }

    @PostConstruct
    public void init(){
        Flux.just(
                new BookEntity(
                        null,
                        "Notes on Her Color",
                        "Jennifer Neal",
                        LocalDate.parse("2023-05-23"),
                        9781646221196L
                ),
                new BookEntity(
                        null,
                        "Zainab Takes New York",
                        "Ayesha Harruna Attah",
                        LocalDate.parse("2023-07-11"),
                        9781472288394L
                ),
                new BookEntity(
                        null,
                        "Yellowface",
                        "R. F. Kuang",
                        LocalDate.parse("2023-05-16"),
                        9780063250833L
                ),
                new BookEntity(
                        null,
                        "Quietly Hostile: Essays",
                        "Samantha Irby ",
                        LocalDate.parse("2022-01-09"),
                        9780593315699L
                ),
                new BookEntity(
                        null,
                        "Safe and Sound: A Renter-Friendly Guide to Home Repair",
                        "Mercury Stardust",
                        LocalDate.parse("2022-04-24"),
                        9780744079074L
                ),
                new BookEntity(
                        null,
                        "This Is How You Lose the Time War",
                        "Amal El-Mohtar",
                        LocalDate.parse("2022-03-06"),
                        9781534430990L
                ),
                new BookEntity(
                        null,
                        "Crying in H Mart: A Memoir",
                        "Michelle Zauner",
                        LocalDate.parse("2020-06-02"),
                        9781984898951L
                ),
                new BookEntity(
                        null,
                        "Let This Radicalize You: Organizing and the Revolution of Reciprocal Care",
                        "Kelly Hayes",
                        LocalDate.parse("2020-11-02"),
                        9781642598278L
                ),
                new BookEntity(
                        null,
                        "Tough Titties: On Living Your Best Life ",
                        "Laura Belgray",
                        LocalDate.parse("2020-08-27"),
                        9780306826047L
                )

        )
                .flatMap(e -> bookEntityRepository.save(e))
                .subscribe();
    }
}
