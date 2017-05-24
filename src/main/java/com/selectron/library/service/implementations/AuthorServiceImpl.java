package com.selectron.library.service.implementations;

import com.selectron.library.model.Author;
import com.selectron.library.repository.AuthorRepository;
import com.selectron.library.service.interfaces.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("authorService")
public class AuthorServiceImpl implements AuthorService {
    @Autowired
    AuthorRepository authorRepository;

    @Override
    public List<Author> getAll() {
        return authorRepository.findAll();
    }

    @Override
    public Author findAuthorById(Integer id) {
        return authorRepository.getOne(id);
    }

    @Override
    public List<Author> findAuthorsByName(String name) {
        List<Author> authors;
        if (name.trim().length() > 5)
            authors = authorRepository.findAuthorsByFirstNameContainsOrLastNameContains(name.trim(),name.trim());
        else {
            authors = authorRepository.findAuthorsByFirstNameStartsWith(name.trim());
            authors.addAll(authorRepository.findAuthorsByLastNameStartsWith(name.trim()));
        }
        return authors;
    }
}
