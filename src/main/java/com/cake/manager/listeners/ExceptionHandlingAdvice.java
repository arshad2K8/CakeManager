package com.cake.manager.listeners;

import com.cake.manager.exceptions.DuplicateCakeException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionHandlingAdvice {

    @ExceptionHandler({DuplicateCakeException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public Map<String, Object>  handleDuplicateCakeException(DuplicateCakeException exception){
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("duplicateCake", exception.getCake());
        return map;
    }

    @ExceptionHandler({DataAccessException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public Map<String, Object>  handleDataIntegrityViolationException(DataAccessException exception){
        return new LinkedHashMap<>();
    }
}
