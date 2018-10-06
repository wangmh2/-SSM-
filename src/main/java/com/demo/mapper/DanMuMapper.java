package com.demo.mapper;

import com.demo.entity.DanMu;

import java.util.List;

public interface DanMuMapper {
    public Integer insert(DanMu danMu);

    public List<DanMu> selectAll();
}
