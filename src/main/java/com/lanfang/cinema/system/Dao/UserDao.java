package com.lanfang.cinema.system.Dao;

import com.lanfang.cinema.system.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Long> {
    User findByEmailAndPassword(String  email , String password);
}
