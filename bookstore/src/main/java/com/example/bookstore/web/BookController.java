package com.example.bookstore.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import com.example.bookstore.domain.Book;
import com.example.bookstore.repository.BookRepository;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
public class BookController {

    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }


    @GetMapping("/index")
    @ResponseBody
    public String index() {
        return "Welcome to the Bookstore!";
    }

    @GetMapping("/booklist")
    public String booklist(Model model) {
        List<Book> books = bookRepository.findAll();
        model.addAttribute("books", books);
        return "booklist";
    }

    @GetMapping("/addbook")
    public String addBookForm(Model model) {
        model.addAttribute("book", new Book());
        return "addbook";
    }

    @PostMapping("/addbook")
    public String addBook(Book book) {
        bookRepository.save(book);
        return "redirect:/booklist";
    }
    
    @GetMapping("/deletebook/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookRepository.deleteById(id);
        return "redirect:/booklist";
    } 

    @GetMapping("/edit/{id}")
    public String editBook(@PathVariable Long id, Model model) {
        Book book = bookRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid book Id:" + id));
                    model.addAttribute("book", book);
                    return "editbook";
    }
    
    @PostMapping("/updatebook")
    public String updateBook(Book book) {
        bookRepository.save(book);
        return "redirect:/booklist";
    }
    
}
