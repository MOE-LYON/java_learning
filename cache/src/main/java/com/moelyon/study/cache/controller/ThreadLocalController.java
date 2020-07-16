package com.moelyon.study.cache.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author moelyon
 */
@RestController
@RequestMapping("thread")
public class ThreadLocalController {

    @GetMapping("TestReveal")
    public String test(){
        System.out.println("模拟内存泄漏");
         new ThreadLocal<Byte[]>().set(new Byte[4096 * 1024]);
        return "ok";
    }
}
