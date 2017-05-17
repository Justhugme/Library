package com.selectron.library.repository;

import com.selectron.library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    public List<Book> findBooksByNameContains(String name);
    public List<Book> findBooksByNameStartsWith(String name);

}
