package com.lanfang.cinema.system.Dao;

import com.lanfang.cinema.system.Domain.Record;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RecordDao extends JpaRepository<Record, Long> {

    @Query(nativeQuery=true,value = "select * from record where user_id = ?1 and f_id = ?2 or user_id =?3 and f_id =?4",
            countQuery = "select  count(*) from record where user_id = ?1 and f_id = ?2 or user_id =?3 and f_id =?4")
    Page<Record> findByUser_idAndF_id(Long user_id,Long f_id,Long u_id,Long fid, Pageable pageable);

    @Query(nativeQuery=true,value = "select * from record where user_id = ?1 ",
            countQuery = "select  count(*) from record where user_id = ?1 ")
    Page<Record> findByUser_id(Long user_id,Pageable pageable);
}
