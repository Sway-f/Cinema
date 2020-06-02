package com.lanfang.cinema.system.Service;

import com.lanfang.cinema.system.Domain.Movies;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MoviesService {
    Movies saveMovies(Movies movies);

    Movies getMovies(Long id);

    Page<Movies> listMovies(Pageable pageable);

    Movies updateMovies(Long id,Movies movies) throws NotFoundException;

    void deleteMovies(Long id);

    Page<Movies> search(String name,Pageable pageable);

    Page<Movies> findbyterm(String type ,String city, String day,Pageable pageable);

    List<Movies> remen();




}
