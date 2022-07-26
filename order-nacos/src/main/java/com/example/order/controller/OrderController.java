package com.example.order.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {
    @RequestMapping("/flow")
    public String flowCtrl() {
        return "每秒访问n次";
    }

    @RequestMapping("/flow/thread")
    public String flowThread() throws InterruptedException {
        Thread.sleep(5000);
        return "每次n个线程访问";
    }

    @RequestMapping("/add")
    public String add() {
        return "新增订单";
    }

    @RequestMapping("/get")
    public String get() {
        return "查询订单";
    }

    @RequestMapping("/test1")
    public String test1() {
        return "test1";
    }

    @RequestMapping("/test2")
    public String test2() {
        return "test2";
    }

    @RequestMapping("/circuit/breaking1")
    public String slowRequestRatio() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "慢调用";
    }

    @RequestMapping("/circuit/breaking2")
    public String errorRatio() {
        throw new RuntimeException("模拟异常");
    }
}
