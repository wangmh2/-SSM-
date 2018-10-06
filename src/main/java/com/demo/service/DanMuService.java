package com.demo.service;

import com.demo.entity.DanMu;

import java.util.List;

public interface DanMuService {
    boolean add(DanMu danMu);

    List<DanMu> findAll();
}
