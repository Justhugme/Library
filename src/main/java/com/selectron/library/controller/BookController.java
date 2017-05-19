package com.selectron.library.controller;

import com.selectron.library.model.Book;
import com.selectron.library.service.interfaces.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/book")
public class BookController {
    @Autowired
    private BookService bookService;

    @RequestMapping(value = "/getAll", method = RequestMethod.POST)
    @ResponseBody
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @RequestMapping(value = "/byName", method = RequestMethod.POST)
    @ResponseBody
    public List<Book> getBookByName(String name) {
        return bookService.findBooksByName(name);
    }



}
