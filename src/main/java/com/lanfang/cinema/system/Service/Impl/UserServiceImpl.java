package com.lanfang.cinema.system.Service.Impl;


import com.lanfang.cinema.system.Domain.User;
import com.lanfang.cinema.system.Service.UserService;
import org.apache.ibatis.javassist.NotFoundException;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private com.lanfang.cinema.system.Dao.UserDao userDao;


    @Override
    public void save(User user) {
        this.userDao.save(user);
        System.out.print("执行数据库插入");
}

    @Override
    public User check(String email, String password) {
        User user = this.userDao.findByEmailAndPassword(email,password);
        Hibernate.initialize(user);
        return user;

    }

    @Override
    public User updateUser(Long id, User user) throws NotFoundException {
        User u =userDao.findById(id).get();
        System.out.println(u.getSex());
        if (u==null) throw new NotFoundException("不存在");
        user.setSex(u.getSex());
        user.setPassword(u.getPassword());
        System.out.println(user.getSex());
        return userDao.save(user);
    }

    @Override
    public User getUser(Long id) {
        return userDao.getOne(id);
    }


}
