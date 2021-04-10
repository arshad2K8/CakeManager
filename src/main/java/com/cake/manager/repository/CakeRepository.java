package com.cake.manager.repository;

import com.cake.manager.domain.Cake;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CakeRepository extends CrudRepository<Cake, Long> {

    @Override
    Cake save(@Param("cake") Cake cake);

    @Override
    List<Cake> findAll();
}
