package com.lanfang.cinema.system.Service;

import com.lanfang.cinema.system.Domain.topMovies;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface topMoviesService {

    topMovies savetopMovies(topMovies topmovies);

    topMovies gettopMovies(Long id);

    Page<topMovies> listtopMovies(Pageable pageable);

    topMovies updatetopMovies(Long id,topMovies topmovies) throws ChangeSetPersister.NotFoundException;

    void deletetopMovies(Long id);

    List<topMovies> sildertopMovies();
}
