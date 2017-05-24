package com.selectron.library.service.interfaces;

import com.selectron.library.model.Author;

import java.util.List;

public interface AuthorService {
    List<Author> getAll();
    Author findAuthorById(Integer id);
    List<Author> findAuthorsByName(String name);
}
