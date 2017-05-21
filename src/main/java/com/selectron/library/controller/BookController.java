package com.selectron.library.controller;

import com.selectron.library.model.Book;
import com.selectron.library.service.interfaces.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value = "books")
public class BookController {
    @Autowired
    private BookService bookService;

    @RequestMapping(value = "/getAll", method = RequestMethod.POST)
    public ModelAndView getAllBooks() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("books",bookService.getAllBooks());
        modelAndView.setViewName("users");
        return modelAndView;
    }

    @RequestMapping(value = "/byName", method = RequestMethod.POST)
    public List<Book> getBookByName(String name) {
        return bookService.findBooksByName(name);
    }



}
