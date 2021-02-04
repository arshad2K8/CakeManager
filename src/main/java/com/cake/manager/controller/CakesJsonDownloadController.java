package com.cake.manager.controller;

import com.cake.manager.domain.Cake;
import com.cake.manager.repository.CakeRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class CakesJsonDownloadController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CakesJsonDownloadController.class);
    private static final String FILE_NAME = "cakes.json";

    private final CakeRepository cakeRepository;

    @Autowired
    public CakesJsonDownloadController(CakeRepository cakeRepository) {
        this.cakeRepository = cakeRepository;
    }

    @RequestMapping(method = GET, value = "/cakes", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public @ResponseBody ResponseEntity<?> downloadCakesAsJson() {
        List<Cake> cakes = cakeRepository.findAll();
        ObjectMapper objectMapper = new ObjectMapper();
        String cakesJson;
        ResponseEntity respEntity;
        try {
            cakesJson = objectMapper.writeValueAsString(cakes);
        } catch (JsonProcessingException e) {
            LOGGER.error("Exception during serialization of cakes", e);
            return ResponseEntity.notFound().build();
        }
        byte[] resp = cakesJson.getBytes();
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("content-disposition", "attachment; filename=" + FILE_NAME);
        responseHeaders.add("Content-Type","text/plain");
        respEntity = new ResponseEntity(resp, responseHeaders, HttpStatus.OK);
        return respEntity;
    }
}
