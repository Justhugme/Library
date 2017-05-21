package com.selectron.library.service.implementations;

import com.selectron.library.model.Author;
import com.selectron.library.model.Book;
import com.selectron.library.model.Rating;
import com.selectron.library.repository.BookRepository;
import com.selectron.library.repository.RatingRepository;
import com.selectron.library.service.interfaces.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("bookService")
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private RatingRepository ratingRepository;

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

    @Override
    public void deleteBook(Integer id) {
        bookRepository.delete(id);
    }

    @Override
    public void saveBook(Book book) {
        bookRepository.save(book);
    }
}
