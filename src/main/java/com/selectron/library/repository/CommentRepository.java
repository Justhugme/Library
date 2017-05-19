package com.selectron.library.repository;

import com.selectron.library.model.Book;
import com.selectron.library.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    List<Comment> findCommentsByBook(Book book);

    void deleteById(Integer id);

    void deleteAllByBook(Book book);
}
