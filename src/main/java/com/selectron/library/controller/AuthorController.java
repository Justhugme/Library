package com.selectron.library.controller;

import com.selectron.library.model.Author;
import com.selectron.library.model.User;
import com.selectron.library.repository.AuthorRepository;
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

import java.util.List;

@Controller
public class AuthorController {
    @Autowired
    private BookService bookService;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private UserService userService;

    @RequestMapping(name = "/authors", method = RequestMethod.GET)
    public ModelAndView getAllAuthors() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        List<Author> authors = authorRepository.findAll();
        authors.sort((o1, o2) -> o1.getFirstName().compareTo(o2.getLastName()));
        modelAndView.addObject("user", user);
        modelAndView.setViewName("Authors");
        return modelAndView;
    }

    @RequestMapping(name = "/author/{id}", method = RequestMethod.GET)
    public ModelAndView getAuthorById(@PathVariable Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        modelAndView.addObject("user",user);
        Author author = authorRepository.findOne(id);
        String wikiUrl = "en.wikipedia.org/wiki/" + author.getFirstName() + "_" + author.getLastName();
        modelAndView.addObject("wikiUrl",wikiUrl);
        modelAndView.addObject("author",author);
        modelAndView.setViewName("Author");
        return modelAndView;
    }


}
