package com.lanfang.cinema.system.Dao;

import com.lanfang.cinema.system.Domain.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface CommentDao extends JpaRepository<Comment,Long>,
        CrudRepository<Comment,Long>, JpaSpecificationExecutor<Comment> {
    @Query(nativeQuery=true,value = "select * from comment where movies_id = ?1 ",
        countQuery = "select  count(*) from comment where movies_id = ?1")
    Page<Comment> findByMovies_id(Long movies_id, Pageable pageable);


}
