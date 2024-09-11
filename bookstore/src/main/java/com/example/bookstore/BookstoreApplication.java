package com.example.bookstore;

import com.example.bookstore.domain.Book;
import com.example.bookstore.domain.Category;
import com.example.bookstore.repository.BookRepository;
import com.example.bookstore.repository.CategoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class BookstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	CommandLineRunner initDatabase(BookRepository bookRepository, CategoryRepository categoryRepository){
		return args -> {
			//create categories
			Category fiction = new Category("Fiction");
            Category nonFiction = new Category("Non-fiction");

			//save categories
			categoryRepository.save(fiction);
            categoryRepository.save(nonFiction);

			//books
			bookRepository.save(new Book("aaa", "bbb", 123, "000", 10.00));
			bookRepository.save(new Book("aaa", "bbb", 123, "000", 10.00));
			bookRepository.save(new Book("aaa", "bbb", 123, "000", 10.00));
		};
	}
}
