package com.lanfang.cinema.system.Dao;

import com.lanfang.cinema.system.Domain.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BlogDao  extends JpaRepository<Blog,Long> {

    @Query(nativeQuery=true,value = "select * from blog where user_id = ?1 ",
            countQuery = "select  count(*) from blog where user_id = ?1")
    Page<Blog> findByUser_id(Long user_id, Pageable pageable);

}
