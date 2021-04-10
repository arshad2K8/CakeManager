package com.cake.manager.listeners;

import com.cake.manager.domain.Cake;
import com.cake.manager.exceptions.DuplicateCakeException;
import com.cake.manager.repository.CakeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;

import java.util.Optional;

@RepositoryEventHandler
public class CakeEventHandler {
    @Autowired
    private CakeRepository cakeRepository;

    @HandleBeforeCreate
    public void handleUserCreate(Cake cake) {

        Optional<Cake> retrievedCake = cakeRepository.findById(cake.getId());
        if(retrievedCake.isPresent() && retrievedCake.get().equals(cake)) {
            throw new DuplicateCakeException("Duplicate Cake Found", cake);
        }

    }
}
