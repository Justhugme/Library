package com.selectron.library.controller;

import com.selectron.library.model.Author;
import com.selectron.library.model.Book;
import com.selectron.library.model.Role;
import com.selectron.library.model.User;
import com.selectron.library.service.interfaces.AuthorService;
import com.selectron.library.service.interfaces.BookService;
import com.selectron.library.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value = "/user")
public class AuthorController {
    @Autowired
    private BookService bookService;
    @Autowired
    private AuthorService authorService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/authors", method = RequestMethod.GET)
    public ModelAndView getAllAuthorsk() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        List<Author> authors = authorService.getAll();
        authors.sort((o1, o2) -> o1.getFirstName().compareTo(o2.getLastName()));
        modelAndView.addObject("user", user);
        modelAndView.addObject("authors", authors);
        modelAndView.setViewName("User/Authors");
        return modelAndView;
    }

    @RequestMapping(value = "/authors/search=", method = RequestMethod.POST)
    public ModelAndView getAuthorsByName(@ModelAttribute(name = "search") Role searchParam) {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        List<Author> authors = authorService.findAuthorsByName(searchParam.getRole().trim());
        authors.sort((o1, o2) -> o1.getFirstName().compareToIgnoreCase(o2.getFirstName()));
        modelAndView.addObject("user", user);
        modelAndView.addObject("search", searchParam);
        modelAndView.addObject("authors", authors);
        modelAndView.setViewName("User/BookList");
        return modelAndView;
    }

    @RequestMapping(value = "/author/{id}", method = RequestMethod.GET)
    public ModelAndView getAuthorById(@PathVariable Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        modelAndView.addObject("user", user);
        Author author = authorService.findAuthorById(id);
        String wikiUrl = "https://en.wikipedia.org/wiki/" + author.getFirstName().trim() + "_" + author.getLastName().trim();
        modelAndView.addObject("wikiUrl", wikiUrl);
        modelAndView.addObject("author", author);
        modelAndView.setViewName("User/Author");
        return modelAndView;
    }


}
