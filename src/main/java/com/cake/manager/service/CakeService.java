package com.cake.manager.service;

import com.cake.manager.domain.Cake;

import java.util.List;

public interface CakeService {
    void persist(Cake cakeEntity);
    List<Cake> findAll();
}
