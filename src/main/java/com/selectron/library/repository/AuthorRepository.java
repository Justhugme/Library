package com.selectron.library.repository;

import com.selectron.library.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {
    List<Author> findAuthorsByFirstNameStartsWith(String firstName);

    List<Author> findAuthorsByLastNameStartsWith(String lastName);

    List<Author> findAuthorsByFirstNameContainsOrLastNameContains(String firstName,String lastName);

}
