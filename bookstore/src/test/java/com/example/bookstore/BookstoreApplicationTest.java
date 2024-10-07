package com.example.bookstore;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.bookstore.web.BookController;

@SpringBootTest
public class BookstoreApplicationTest {

    private final BookController bcontroller;

    @Autowired
    public BookstoreApplicationTest(BookController bcontroller){
        this.bcontroller = bcontroller;
    }

    @Test
    void contextLoads(){

    }

    @Test
    public void bcontrollerLoads() throws Exception {
        assertThat(bcontroller).isNotNull();
    }

}
