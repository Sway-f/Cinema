package com.lanfang.cinema.system.Service.Impl;


import com.lanfang.cinema.system.Dao.blogCommentDao;
import com.lanfang.cinema.system.Domain.blogComment;
import com.lanfang.cinema.system.Service.blogCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class blogCommentServiceImpl implements blogCommentService {
    @Autowired
    blogCommentDao blogcommentDao;

    @Override
    public blogComment saveblogComment(blogComment blogcomment) {
        return blogcommentDao.save(blogcomment);
    }

    @Override
    public blogComment getblogComment(Long id) {
        return blogcommentDao.getOne(id);
    }

    @Override
    public Page<blogComment> listblogComment(Pageable pageable) {
        return blogcommentDao.findAll(pageable);
    }

    @Override
    public void deletblogComment(Long id) {
        blogcommentDao.deleteById(id);
    }

    @Override
    public Page<blogComment> getblogCommentByblog_id(Long blog_id, Pageable pageable) {
        return blogcommentDao.findByBlog_id(blog_id,pageable);
    }
}
