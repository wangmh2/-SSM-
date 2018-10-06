package com.demo.service;

import com.demo.entity.beiwang;
import com.demo.mapper.BeiwangMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
@Service
public class BeiwangServiceImpl implements BeiwangService{
    @Autowired
    private BeiwangMapper bm;
    @Override
    public void addbeiwang(beiwang beiwang) {
        bm.addbeiwang(beiwang);
    }

    @Override
    public void deletebeiwang(Timestamp beiwangtime) {
        bm.deletebeiwang(beiwangtime);
    }
}
