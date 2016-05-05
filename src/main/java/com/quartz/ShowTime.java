package com.quartz;

import org.springframework.stereotype.Component;

@Component
public class ShowTime {

    public void show(){
        System.out.println("Quartz执行了！");
    }
}
