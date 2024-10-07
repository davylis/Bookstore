package com.example.bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.bookstore.domain.Book;
import com.example.bookstore.domain.Category;
import com.example.bookstore.repository.BookRepository;

@DataJpaTest
public class BookRepoTest {
    @Autowired
    private BookRepository brepository;

    @Test
    public void findByTitleShouldReturnBook(){
        List<Book> books = brepository.findByTitle("The Best of Me");
        assertThat(books).hasSize(1);
        assertThat(books.get(0).getAuthor()).isEqualTo("Nicholas Sparks");
    }

    @Test
    public void testCreateNewBook(){
        Category category = new Category("Fiction");
        Book book = new Book("mmm", "mickey", 2024, "12345", 20.0, category);
        brepository.save(book);
        assertThat(book.getId()).isNotNull();
    }

    @Test
    public void testDeleteBook(){
        List<Book> books = brepository.findByTitle("The Best of Me");
        brepository.deleteById(books.get(0).getId());
        books = brepository.findByTitle("The Best of Me");
        assertThat(books).hasSize(0);
    }

}
