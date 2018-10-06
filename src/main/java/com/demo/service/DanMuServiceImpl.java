package com.demo.service;

import com.demo.entity.DanMu;
import com.demo.mapper.DanMuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DanMuServiceImpl implements DanMuService {

    @Autowired
    private DanMuMapper danMuMapper;
    @Override
    public boolean add(DanMu danMu) {
        Integer x = danMuMapper.insert(danMu);
        return x != null && x > 0;
    }

    @Override
    public List<DanMu> findAll() {
        List<DanMu> danMus = danMuMapper.selectAll();
        
        return danMus;
    }
}
