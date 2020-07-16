package com.moelyon.study.cache.service;

import com.moelyon.study.cache.entity.Coffee;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * @author moelyon
 */
@Service
@Slf4j
public class CoffeeService {

    private final static String CACHE ="MoeLyon.Coffee";


    public Optional<Coffee> getCoffee(Long id) {


        Coffee coffee = Coffee.builder()
                .id(id)
                .name("lattern")
                .price(233).build();
        log.info("no cache search from db");

        return Optional.of(coffee);
    }
}
