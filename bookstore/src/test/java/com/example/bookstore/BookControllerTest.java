package com.example.bookstore;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.containsString;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
// Import the test security configuration to bypass the login
@Import(TestSecurityConfig.class) //import to bypass login
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void testGetBooks() throws Exception {
        this.mockMvc.perform(get("/api/books"))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$._embedded.books").exists());
    }

    @Test
    public void testGetBookById() throws Exception {
        //testing the "/books" endpoint
        this.mockMvc.perform(get("/api/book/1"))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.title").value("The Best of Me"));    
    }

   
    @Test
    public void testDeleteBook() throws Exception {
        this.mockMvc.perform(get("/api/book/1"))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.title").value("The Best of Me"));

                    this.mockMvc.perform(get("/book/delete/1"))
                    .andDo(print())
                    .andExpect(content().string(containsString("Book deleted")));

                    this.mockMvc.perform(get("/book/1"))
                    .andDo(print())
                    .andExpect(status().isNotFound());
    }

}
