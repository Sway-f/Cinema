package com.lanfang.cinema.system.Service.Impl;

import com.lanfang.cinema.system.Dao.ReDao;
import com.lanfang.cinema.system.Domain.Re;
import com.lanfang.cinema.system.Service.ReService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class ReServiceImpl implements ReService {
    @Autowired
    ReDao reDao;

    @Override
    public Re saveRe(Re re) {
        return reDao.save(re);
    }

    @Override
    public List<Re> getRecommend(Long user_id) {
        return reDao.findByUser_id(user_id);
    }
}
