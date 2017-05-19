package com.selectron.library.service.interfaces;

import com.selectron.library.model.Author;
import com.selectron.library.model.Book;

import java.util.List;

public interface BookService {
    List<Book> getAllBooks();

    List<Book> findBooksByName(String name);

    Book findBookById(Integer id);

    List<Book> findBooksWhereAuthor(Author author);

    void deleteBook(Integer id);

    void saveBook(Book book);
}
