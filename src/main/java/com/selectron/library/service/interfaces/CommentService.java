package com.selectron.library.service.interfaces;

import com.selectron.library.model.Book;
import com.selectron.library.model.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> getAllCommentsByBook(Book book);
    void deleteComentById(Integer id);
    void addComentToBook(Book book);
    void deleteAllcomentsByBook(Book book);

}
