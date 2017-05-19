package com.selectron.library.repository;

import com.selectron.library.model.Book;
import com.selectron.library.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Integer> {
    public List<Comment> findCommentsByBook(Book book);
    public void deleteById(Integer id);
    public void deleteAllByBook(Book book);
}
