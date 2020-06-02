package com.lanfang.cinema.system.Service.Impl;


import com.lanfang.cinema.system.Dao.newMoviesDao;
import com.lanfang.cinema.system.Domain.Movies;
import com.lanfang.cinema.system.Domain.newMovies;
import com.lanfang.cinema.system.Service.newMoviesService;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class newMoviesServiceImpl implements newMoviesService {
    @Autowired
    private newMoviesDao newmoviesDao;

    @Override
    public newMovies savenewMovies(newMovies newmovies) {
        return newmoviesDao.save(newmovies);
    }

    @Override
    public newMovies getnewMovies(Long id) {
        return newmoviesDao.getOne(id);
    }

    @Override
    public Page<newMovies> listnewMovies(Pageable pageable) {
        return newmoviesDao.findAll(pageable);
    }

    @Override
    public newMovies updatenewMovies(Long id, newMovies newmovies) throws NotFoundException {
        return null;
    }

    @Override
    public void deletenewMovies(Long id) {
        newmoviesDao.deleteById(id);

    }

    @Override
    public List<newMovies> sildernewMovies() {
        return newmoviesDao.silder();
    }
}
