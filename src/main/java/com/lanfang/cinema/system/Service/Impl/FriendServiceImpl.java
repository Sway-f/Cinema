package com.lanfang.cinema.system.Service.Impl;

import com.lanfang.cinema.system.Dao.FriendDao;
import com.lanfang.cinema.system.Domain.Friend;
import com.lanfang.cinema.system.Service.FriendService;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Transactional
@Service
public class FriendServiceImpl implements FriendService {
    @Autowired
    FriendDao friendDao;

    @Override
    public Friend saveFriend(Friend friend) {
        return friendDao.save(friend);
    }

    @Override
    public void deletFriend(Long id) {
        friendDao.deleteById(id);
    }

    @Override
    public Friend findExit(Long user_id, Long f_id) {
        return friendDao.findByUser_idAndAndF_id(user_id,f_id);
    }

    @Override
    public Page<Friend> getFriendByUser_id(Long user_id, Pageable pageable) {
        return friendDao.findByUser_id(user_id,pageable);
    }

    @Override
    public void update(Long f_id, String imageurl) throws NotFoundException {
        List<Friend> f =friendDao.findByF_id(f_id);
        if (f==null) throw new NotFoundException("不存在");
        for(int i=0;i<f.size();i++){
            f.get(i).setF_image(imageurl);
        }
    }


}
