package com.lanfang.cinema.system.Service.Impl;

import com.lanfang.cinema.system.Dao.BlogDao;
import com.lanfang.cinema.system.Domain.Blog;
import com.lanfang.cinema.system.Service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    BlogDao blogDao;
    @Override
    public Blog saveBlog(Blog blog) {
        return blogDao.save(blog);
    }

    @Override
    public Blog getBlog(Long id) {
        return blogDao.getOne(id);
    }

    @Override
    public Page<Blog> listBlog(Pageable pageable) {
        return blogDao.findAll(pageable);
    }

    @Override
    public void deletBlog(Long id) {
        blogDao.deleteById(id);
    }

    @Override
    public Page<Blog> getBlogByUser_id(Long user_id, Pageable pageable) {
        return blogDao.findByUser_id(user_id,pageable);
    }
}
