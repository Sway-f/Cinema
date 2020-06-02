package com.lanfang.cinema.system.Service;

import com.lanfang.cinema.system.Domain.Friend;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FriendService {
    Friend saveFriend(Friend friend);

    void  deletFriend(Long id);

    Friend findExit(Long user_id,Long f_id);

    Page<Friend> getFriendByUser_id(Long user_id, Pageable pageable);

    void update(Long f_id,String imageurl)throws NotFoundException;
}
