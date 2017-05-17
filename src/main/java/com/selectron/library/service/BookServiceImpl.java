package com.selectron.library.service;

import com.selectron.library.model.Author;
import com.selectron.library.model.Book;
import com.selectron.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("bookService")
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> findBooksByName(String name) {
        return bookRepository.findBooksByNameStartsWith(name);
    }

    @Override
    public Book findBookById(Integer id) {
        return bookRepository.findOne(id);
    }

    @Override
    public List<Book> findBooksWhereAuthor(Author author) {
        return bookRepository.findBooksByAuthors(author);
    }
}
