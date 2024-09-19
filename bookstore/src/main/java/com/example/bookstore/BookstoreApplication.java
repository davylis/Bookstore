package com.example.bookstore;

import com.example.bookstore.domain.Book;
import com.example.bookstore.domain.Category;
import com.example.bookstore.repository.BookRepository;
import com.example.bookstore.repository.CategoryRepository;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BookstoreApplication {
	//is used to output log messages, which can help debugging, monitoring and maintain application
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

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

			//creating books and associating them with categories
			bookRepository.save(new Book("aaa", "bbb", 123, "000", 10.00, fiction));
			bookRepository.save(new Book("aaa", "bbb", 123, "000", 10.00, nonFiction));
			bookRepository.save(new Book("aaa", "bbb", 123, "000", 10.00, fiction));
		};
	}
}
