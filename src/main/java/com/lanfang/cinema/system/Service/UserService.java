package com.lanfang.cinema.system.Service;

import com.lanfang.cinema.system.Domain.User;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserService  {


    void save(User user);
    User check(String email, String password);
    User updateUser(Long id,User user) throws NotFoundException;
    User getUser(Long id);

}
