package com.demo.service;

import com.demo.entity.beiwang;

import java.sql.Timestamp;

public interface BeiwangService {
    //添加备忘
    public void addbeiwang(beiwang beiwang);

    //根据时间删除备忘
    public void deletebeiwang(Timestamp beiwangtime);
}
