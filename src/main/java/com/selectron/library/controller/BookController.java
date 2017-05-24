package com.selectron.library.controller;

import com.selectron.library.model.Book;
import com.selectron.library.model.Comment;
import com.selectron.library.model.Role;
import com.selectron.library.model.User;
import com.selectron.library.service.interfaces.BookService;
import com.selectron.library.service.interfaces.CommentService;
import com.selectron.library.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping(value = "/user")
public class BookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private UserService userService;
    @Autowired
    private CommentService commentService;

    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public ModelAndView getAllBooks() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        modelAndView.addObject("user", user);
        modelAndView.addObject("search", new Role());
        modelAndView.addObject("books", bookService.getAllBooks());
        modelAndView.setViewName("User/BookList");
        return modelAndView;
    }

    @RequestMapping(value = "/books/search=", method = RequestMethod.POST)
    public ModelAndView getBooksByName(@ModelAttribute(name = "search") Role searchParam) {
        ModelAndView modelAndView = new ModelAndView();
        System.out.println("____________________------------------ " + searchParam.getRole());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        List<Book> books = bookService.findBooksByName(searchParam.getRole().trim());
        books.sort((o1, o2) -> o1.getName().compareToIgnoreCase(o2.getName()));
        modelAndView.addObject("user", user);
        modelAndView.addObject("search", searchParam);
        modelAndView.addObject("books", books);
        modelAndView.setViewName("User/BookList");
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
        modelAndView.setViewName("User/WishList");
        return modelAndView;
    }

    @RequestMapping(value = "/WishList/add", method = RequestMethod.POST)
    public ModelAndView addBookToWishList(@RequestParam("BookId") Integer bookId) {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        Set<Book> wishlist = user.getWhishList();
        Book book = bookService.findBookById(bookId);
        wishlist.add(book);
        user.setWhishList(wishlist);
        userService.saveUser(user);
        modelAndView.setViewName("User/BookList");
        modelAndView.addObject("search", new Role());
        modelAndView.addObject("books", bookService.getAllBooks());
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @RequestMapping(value = "/WishList/remove", method = RequestMethod.POST)
    public ModelAndView removeBookFromWishList(@RequestParam("BookId") Integer bookId) {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        Set<Book> wishlist = user.getWhishList();
        Book book = bookService.findBookById(bookId);
        wishlist.remove(book);
        user.setWhishList(wishlist);
        userService.saveUser(user);
        modelAndView.addObject("user", user);
        List<Book> wishList = new ArrayList<>(user.getWhishList());
        modelAndView.addObject("books", wishList);
        modelAndView.setViewName("User/WishList");
        return modelAndView;
    }


    @RequestMapping(value = "/book/id={id}", method = RequestMethod.GET)
    public ModelAndView getBookPage(@PathVariable Integer id) {
        Comment newComment = new Comment();
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        Book book = bookService.findBookById(id);
        modelAndView.addObject("user", user);
        modelAndView.addObject("newComment", newComment);

        modelAndView.addObject("book", book);
        modelAndView.setViewName("User/BookInfo");
        return modelAndView;
    }

    @RequestMapping(value = "/book/id={id}/addComment", method = RequestMethod.POST)
    public ModelAndView addComment(@PathVariable Integer id, Comment comment) {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        Book book = bookService.findBookById(id);
        Comment newComment = new Comment();
        comment.setUser(user);
        comment.setBook(book);
        Date date = new Date(Calendar.getInstance().getTime().getTime());
        comment.setDate(date);
        comment.setId(commentService.getIdForComment(comment));
        commentService.saveComment(comment);
        System.out.println("---------------------INFO-------------");
        System.out.println("id book = " + comment.getBook().getId());
        System.out.println("comment content = " + comment.getContent());
        System.out.println("comment id = " + comment.getId());
        System.out.println("user id = " + comment.getUser().getId());
        System.out.println("date = " + comment.getDate());
        System.out.println("---------------------INFO-------------");
        modelAndView.addObject("user", user);
        modelAndView.addObject("newComment", newComment);
        modelAndView.addObject("book", book);
        modelAndView.setViewName("User/BookInfo");
        return modelAndView;
    }
}
