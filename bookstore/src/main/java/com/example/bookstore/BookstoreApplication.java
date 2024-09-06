package com.example.bookstore;

import com.example.bookstore.domain.Book;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.bookstore.repository.BookRepository;

@SpringBootApplication
public class BookstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	CommandLineRunner initDatabase(BookRepository bookRepository){
		return args -> {
			bookRepository.save(new Book("aaa", "bbb", 123, "000", 10.00));
			bookRepository.save(new Book("aaa", "bbb", 123, "000", 10.00));
			bookRepository.save(new Book("aaa", "bbb", 123, "000", 10.00));
		};
	}
}
