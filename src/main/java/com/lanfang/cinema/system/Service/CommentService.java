package com.lanfang.cinema.system.Service;

import com.lanfang.cinema.system.Domain.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface CommentService {
    Comment saveComment(Comment comment);

    Comment getComment(Long id);

    Page<Comment> listComment(Pageable pageable);

    void  deletComment(Long id);

    Page<Comment> getCommentByMovies_id(Long movies_id, Pageable pageable);






}
