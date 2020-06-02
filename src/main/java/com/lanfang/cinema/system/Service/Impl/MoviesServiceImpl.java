package com.lanfang.cinema.system.Service.Impl;

import com.lanfang.cinema.system.Dao.MoviesDao;
import com.lanfang.cinema.system.Domain.Movies;
import com.lanfang.cinema.system.Service.MoviesService;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class MoviesServiceImpl implements MoviesService {
    @Autowired
    private MoviesDao moviesDao;

    @Override
    public Movies saveMovies(Movies movies) {
        return moviesDao.save(movies);
    }


    @Override
    public Movies getMovies(Long id) {
        return moviesDao.getOne(id);
    }

    @Override
    public Page<Movies> listMovies(Pageable pageable) {
        return moviesDao.findAll(pageable);
    }


    @Override
    public Movies updateMovies(Long id, Movies movies) throws NotFoundException {
        Movies m =moviesDao.findById(id).get();
        if (m==null) throw new NotFoundException("不存在");
        BeanUtils.copyProperties(movies,m);
        return moviesDao.save(movies);
    }


    @Override
    public void deleteMovies(Long id) {
        moviesDao.deleteById(id);

    }

    @Override
    public Page<Movies> search(String name,Pageable pageable) {
        return moviesDao.findByNameLike(name,pageable);
    }

    @Override
    public Page<Movies> findbyterm(String type, String city, String day, Pageable pageable) {
        return moviesDao.findByTypeLikeAndCityLikeAndDatelineLike(type,city,day,pageable);
    }

    @Override
    public List<Movies> remen() {
        return moviesDao.remen();
    }


}
