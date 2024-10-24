package com.example.bookstore;

import com.example.bookstore.domain.AppUser;
import com.example.bookstore.domain.Book;
import com.example.bookstore.domain.Category;
import com.example.bookstore.repository.AppUserRepository;
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
	CommandLineRunner initDatabase(BookRepository bookRepository, CategoryRepository categoryRepository, 
			AppUserRepository userRepository){
		return args -> {
			//create categories
			Category fiction = new Category("Fiction");
            Category nonFiction = new Category("Non-fiction");
			Category romance = new Category("Romance");

			//save categories
			categoryRepository.save(fiction);
            categoryRepository.save(nonFiction);
			categoryRepository.save(romance);

			//create users
			AppUser user1 = new AppUser("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "ROLE_USER", 
							"user@email.fi");
			AppUser user2 = new AppUser("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ROLE_ADMIN", 
							"admin@email.fi");
			//Comment this, because database already saved them
			//userRepository.save(user1);
			//userRepository.save(user2);

			log.info("fetch all students");
			for (Book book : bookRepository.findAll()) {
				log.info(book.toString());
			}

			//creating books and associating them with categories
			bookRepository.save(new Book("The Best of Me", "Nicholas Sparks", 2011, "9781455502547", 20.00, romance));
			bookRepository.save(new Book("aaa", "bbb", 123, "000", 10.00, nonFiction));
			bookRepository.save(new Book("aaa", "bbb", 123, "000", 10.00, fiction));
		};
	}
}
