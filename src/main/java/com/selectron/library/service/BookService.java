package com.selectron.library.service;

import com.selectron.library.model.Author;
import com.selectron.library.model.Book;

import java.util.List;

public interface BookService {
    public List<Book> getAllBooks();
    public List<Book> findBooksByName(String name);
    public Book findBookById(Integer id);
    public List<Book> findBooksWhereAuthor(Author author);
}
