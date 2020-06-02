package com.lanfang.cinema.system.Dao;

import com.lanfang.cinema.system.Domain.Re;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReDao extends JpaRepository<Re,Long> {
    @Query(nativeQuery=true,value = "select * from re where user_id = ?1 order by score ASC limit 18")
    List<Re> findByUser_id(Long user_id);

}
