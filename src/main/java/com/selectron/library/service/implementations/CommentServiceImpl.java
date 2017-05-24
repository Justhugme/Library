package com.selectron.library.service.implementations;

import com.selectron.library.model.Book;
import com.selectron.library.model.Comment;
import com.selectron.library.repository.CommentRepository;
import com.selectron.library.service.interfaces.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Random;

@Service("commentService")
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Override
    public List<Comment> getAllCommentsByBook(Book book) {
        return commentRepository.findCommentsByBook(book);
    }

    @Override
    public void saveComment(Comment comment) {
        commentRepository.save(comment);
    }

    @Override
    public int getIdForComment(Comment comment) {
        Integer id = comment.getId();
        if (commentRepository.exists(id)) {
            List<Comment> comments = commentRepository.findAll();
            return comments.get(comments.size() - 1).getId() + 1;
        }
        return id;
    }
}
