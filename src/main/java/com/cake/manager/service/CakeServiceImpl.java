package com.cake.manager.service;

import com.cake.manager.domain.Cake;
import com.cake.manager.repository.CakeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CakeServiceImpl implements CakeService{

    private final CakeRepository cakeRepository;

    @Autowired
    public CakeServiceImpl(CakeRepository cakeRepository) {
        this.cakeRepository = cakeRepository;
    }

    @Override
    public void persist(Cake cake) {
        //TODO validation ?
        cakeRepository.save(cake);
    }

    @Override
    public List<Cake> findAll() {
        return cakeRepository.findAll();
    }
}
