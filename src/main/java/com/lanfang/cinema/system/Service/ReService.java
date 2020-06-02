package com.lanfang.cinema.system.Service;

import com.lanfang.cinema.system.Domain.Re;

import java.util.List;

public interface ReService {
    Re saveRe(Re re);
    List<Re> getRecommend(Long user_id);
}
