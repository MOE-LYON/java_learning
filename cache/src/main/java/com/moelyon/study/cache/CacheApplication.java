package com.moelyon.study.cache;

import com.moelyon.study.cache.entity.Coffee;
import com.moelyon.study.cache.service.CoffeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class CacheApplication  {

    public static void main(String[] args) {
        SpringApplication.run(CacheApplication.class, args);
    }

}
