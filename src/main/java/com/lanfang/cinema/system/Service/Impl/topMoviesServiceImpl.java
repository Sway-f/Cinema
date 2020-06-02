package com.lanfang.cinema.system.Service.Impl;

import com.lanfang.cinema.system.Dao.topMoviesDao;
import com.lanfang.cinema.system.Domain.topMovies;
import com.lanfang.cinema.system.Service.topMoviesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class topMoviesServiceImpl implements topMoviesService {
    @Autowired
    private topMoviesDao topmoviesDao;
    @Override
    public topMovies savetopMovies(topMovies topmovies) {
        return topmoviesDao.save(topmovies);
    }

    @Override
    public topMovies gettopMovies(Long id) {
        return topmoviesDao.getOne(id);
    }

    @Override
    public Page<topMovies> listtopMovies(Pageable pageable) {
        return topmoviesDao.findAll(pageable);
    }

    @Override
    public topMovies updatetopMovies(Long id, topMovies topmovies) throws ChangeSetPersister.NotFoundException {
        return null;
    }

    @Override
    public void deletetopMovies(Long id) {
        topmoviesDao.deleteById(id);
    }

    @Override
    public List<topMovies> sildertopMovies() {
        return topmoviesDao.silder();
    }
}
