package com.lanfang.cinema.system.Service.Impl;

import com.lanfang.cinema.system.Dao.CommentDao;
import com.lanfang.cinema.system.Domain.Comment;
import com.lanfang.cinema.system.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentDao commentDao;
    @Override
    public Comment saveComment(Comment comment) {
        return commentDao.save(comment);
    }

    @Override
    public Comment getComment(Long id) {
        return commentDao.getOne(id);
    }

    @Override
    public Page<Comment> listComment(Pageable pageable) {
        return commentDao.findAll(pageable);
    }

    @Override
    public void deletComment(Long id) {
        commentDao.deleteById(id);

    }

    @Override
    public Page<Comment> getCommentByMovies_id(Long movies_id, Pageable pageable) {
        return commentDao.findByMovies_id(movies_id,pageable);
    }




}
