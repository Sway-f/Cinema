package com.lanfang.cinema.system.Dao;

import com.lanfang.cinema.system.Domain.Movies;
import com.lanfang.cinema.system.Domain.newMovies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface newMoviesDao extends JpaRepository<newMovies,Long> {
    @Query(nativeQuery=true,value = "select * from new_movies order by id ASC limit 6")
    List<newMovies> silder();
}
