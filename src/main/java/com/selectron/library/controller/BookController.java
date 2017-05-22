package com.selectron.library.controller;

import com.selectron.library.model.Book;
import com.selectron.library.model.User;
import com.selectron.library.service.interfaces.BookService;
import com.selectron.library.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public ModelAndView getAllBooks() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        modelAndView.addObject("user", user);
        modelAndView.addObject("searchParam", "");
        modelAndView.addObject("books", bookService.getAllBooks());
        modelAndView.setViewName("b");
        return modelAndView;
    }

    @RequestMapping(value = "/book/{searchParam}", method = RequestMethod.GET)
    public ModelAndView getBooksByName(@PathVariable String searchParam) {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        modelAndView.addObject("user", user);
        modelAndView.addObject("books", bookService.findBooksByName(searchParam));
        modelAndView.setViewName("a");
        return modelAndView;
    }

    @RequestMapping(value = "/WishList", method = RequestMethod.GET)
    public ModelAndView getWishListBooks() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        modelAndView.addObject("user", user);
        List<Book> wishList = new ArrayList<>(user.getWhishList());
        modelAndView.addObject("books", wishList);
        modelAndView.setViewName("WishList");
        return modelAndView;
    }


    @RequestMapping(value = "/book/{id}", method = RequestMethod.GET)
    public ModelAndView getBookPage(@PathVariable Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        modelAndView.addObject("user", user);
        modelAndView.addObject("book", bookService.findBookById(id));
        modelAndView.setViewName("BookInfo");
        return modelAndView;
    }


}
