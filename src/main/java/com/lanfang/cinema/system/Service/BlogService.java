package com.lanfang.cinema.system.Service;

import com.lanfang.cinema.system.Domain.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BlogService {

    Blog saveBlog(Blog blog);

    Blog getBlog(Long id);

    Page<Blog> listBlog(Pageable pageable);

    void  deletBlog(Long id);

    Page<Blog> getBlogByUser_id(Long user_id, Pageable pageable);

}
