package com.moelyon.study.cache.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * @author moelyon
 */
@RestController
@RequestMapping("thread")
public class ThreadLocalController {

    private static class Val<E>{
        public E data;

        public Val(E data) {
            this.data = data;
        }
    }

    private Set<Val<Integer>> set = new ConcurrentSkipListSet<>();

    private ThreadLocal<Val<Integer>> threadLocal = ThreadLocal.withInitial(()->{
        Val<Integer> data = new Val<>(0);
        set.add(data);
        return data;
    });


    /**
     *
     * 测试内存泄露
     *
     * @return 233
     */
    @GetMapping("TestReveal")
    public String test(){
        System.out.println("模拟内存泄漏");
         new ThreadLocal<Byte[]>().set(new Byte[4096 * 1024]);
        return "ok";
    }
}
