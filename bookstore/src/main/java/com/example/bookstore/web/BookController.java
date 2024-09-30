package com.example.bookstore.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.bookstore.domain.Book;
import com.example.bookstore.repository.BookRepository;
import com.example.bookstore.repository.CategoryRepository;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    // Show all students
    @RequestMapping(value="/login")
    public String login() {	
        return "login";
    }	

    @RequestMapping(value = {"/", "/booklist"})
    public String booklist(Model model) {
       model.addAttribute("books", bookRepository.findAll());
        return "booklist";
    }

    @RequestMapping("/addbook")
    public String addBookForm(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("categories", categoryRepository.findAll());
        return "addbook";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Book book) {
        bookRepository.save(book);
        return "redirect:/booklist";
    }
    
    @RequestMapping(value = "/deletebook/{id}", method = RequestMethod.GET)
    public String deleteBook(@PathVariable("id") Long id, Model model) {
        bookRepository.deleteById(id);
        return "redirect:/booklist";
    } 

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editBook(@PathVariable("id") Long id, Model model) {
        Book book = bookRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid book Id:" + id));
        model.addAttribute("book", book);
        model.addAttribute("categories", categoryRepository.findAll());
        return "editbook";
    }

    //RESTful service to get all students
    //8080/books
    @RequestMapping(value = "/books", method=RequestMethod.GET)
    public @ResponseBody List<Book> bookListRest() {
        return (List<Book>) bookRepository.findAll();
    }

    //RESTful service to get a book by an id
    //8080/book/(id)
    @RequestMapping(value = "/book/{id}", method=RequestMethod.GET)
    public @ResponseBody Book findBookRest(@PathVariable("id") Long id){
        Optional<Book> book = bookRepository.findById(id);
        return book.orElse(null);
    }

    //RESTful service for adding a new book
    //Testing POSTMAN
    @RequestMapping(value = "/book/add", method=RequestMethod.POST)
    public @ResponseBody Book addBooks(@RequestBody Book newBook) {
        return bookRepository.save(newBook);
    }
    
    //RESTful service for finding a book by title
    //Testing POSTMAN
    @RequestMapping(value="books/search/title", method=RequestMethod.GET)
    public @ResponseBody List <Book> findBookByTitle(@RequestParam("title") String title) {
        return bookRepository.findByTitle(title);
    }


    
}
