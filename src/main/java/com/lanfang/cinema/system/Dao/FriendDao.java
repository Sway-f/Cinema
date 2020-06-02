package com.lanfang.cinema.system.Dao;

import com.lanfang.cinema.system.Domain.Friend;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FriendDao extends JpaRepository<Friend, Long> {
    @Query(nativeQuery=true,value = "select * from friend where user_id = ?1 ",
            countQuery = "select  count(*) from friend where user_id = ?1")
    Page<Friend> findByUser_id(Long user_id, Pageable pageable);

    @Query(nativeQuery=true,value = "select * from friend where user_id = ?1 and f_id = ?2")
    Friend findByUser_idAndAndF_id(Long user_id,Long f_id);

    @Query(nativeQuery=true,value = "select * from friend where f_id = ?1")
    List<Friend> findByF_id(Long f_id);
}

