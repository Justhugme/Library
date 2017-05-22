package com.selectron.library.controller;

import com.selectron.library.service.interfaces.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "books")
public class BookController {
    @Autowired
    private BookService bookService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView getAllBooks() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("searchParam", "");
        modelAndView.addObject("books", bookService.getAllBooks());
        modelAndView.setViewName("b"); //TODO олег напиши вюшку яка бдуе відображати всі книжки
        return modelAndView;
    }

    @RequestMapping(value = "/{searchParam}", method = RequestMethod.GET)
    public ModelAndView getBooksByName(@PathVariable String searchParam) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("books", bookService.findBooksByName(searchParam));
        modelAndView.setViewName("a");//TODO 1 вюшка на оці 2 методи
        return modelAndView;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ModelAndView getBookPage(@PathVariable Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("book", bookService.findBookById(id));
        return modelAndView;
    }


}
