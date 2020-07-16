package com.moelyon.study.cache.controller;

import com.moelyon.study.cache.entity.Coffee;
import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.lang.NonNull;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.util.UriTemplate;

import java.util.List;
import java.util.Locale;

/**
 * @author moelyon
 */
@RestController
@RequestMapping("/hello")
@Slf4j
public class HelloController {


    @GetMapping("")
    public String hello(){
        return "HELLO master";
    }

    @Data
    @ToString
    public  static class CoffeeOrder{
        @NonNull
        private String name;
        @NonNull
        private List<String> coffees;
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder){
        return  builder.build();
    }

    @Autowired
    RestTemplate restTemplate;

    @PostMapping(value = "/coffee",consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public String bye(@RequestParam(required = false) String name, @RequestBody @Validated CoffeeOrder coffeeOrder, BindingResult result){
        log.info(result.toString());
        ParameterizedTypeReference<List<Coffee>> pty = new ParameterizedTypeReference<List<Coffee>>() {};

        UriComponents components = UriComponentsBuilder.fromHttpUrl("")
                .buildAndExpand(1212);
        RequestEntity<Void> entity = RequestEntity
                .get(components.toUri())
                .build();

        restTemplate.exchange(entity,String.class);

        log.info(coffeeOrder.toString());
        return "coffee " + name;
    }

    @GetMapping(value = "",params ={"name"})
    public String hello(@RequestParam() String name){
        return String.format(Locale.CHINESE,"HELLO %s",name);
    }
}
