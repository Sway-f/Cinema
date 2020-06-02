package com.lanfang.cinema.system.Dao;

import com.lanfang.cinema.system.Domain.blogComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface blogCommentDao extends JpaRepository<blogComment,Long> {

    @Query(nativeQuery=true,value = "select * from blogcomment where blog_id = ?1 ",
            countQuery = "select  count(*) from blogcomment where blog_id = ?1")
    Page<blogComment> findByBlog_id(Long blog_id, Pageable pageable);

}
