package com.lanfang.cinema.system.Dao;

import com.lanfang.cinema.system.Domain.Movies;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MoviesDao extends JpaRepository<Movies,Long> {


    Page<Movies> findByNameLike(String name, Pageable pageable);
    Page<Movies> findByTypeLikeAndCityLikeAndDatelineLike(String type , String city, String day,Pageable pageable);

    @Query(nativeQuery=true,value = "select * from movies order by id ASC limit 10")
    List<Movies> remen();
}
