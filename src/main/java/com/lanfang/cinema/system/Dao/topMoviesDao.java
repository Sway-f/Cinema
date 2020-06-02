package com.lanfang.cinema.system.Dao;

import com.lanfang.cinema.system.Domain.topMovies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface topMoviesDao extends JpaRepository<topMovies,Long> {

    @Query(nativeQuery=true,value = "select * from top_movies order by id ASC limit 3")
    List<topMovies> silder();
}
