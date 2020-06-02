package com.lanfang.cinema.system.Service;

import com.lanfang.cinema.system.Domain.newMovies;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface newMoviesService {
    newMovies savenewMovies(newMovies newmovies);

    newMovies getnewMovies(Long id);

    Page<newMovies> listnewMovies(Pageable pageable);

    newMovies updatenewMovies(Long id,newMovies newmovies) throws NotFoundException;

    void deletenewMovies(Long id);

    List<newMovies> sildernewMovies();

}
