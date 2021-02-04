package com.cake.manager.repository;

import com.cake.manager.domain.Cake;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CakeRepository extends PagingAndSortingRepository<Cake, Long> {

    @Override
    //@PreAuthorize("#employee?.manager == null or #employee?.manager?.name == authentication?.name")
    Cake save(@Param("cake") Cake cake);

    @Override
    List<Cake> findAll();
}
