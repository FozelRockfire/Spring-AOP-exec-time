package com.t1study.aopspring.service;


import com.t1study.aopspring.annotation.TrackTime;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

@Service
public class AnnotationTestService {

    @SneakyThrows
    @TrackTime
    public void threeSecSleep(){
        System.out.println("буду ждать");
        Thread.sleep(3000);
        System.out.println("подождал");
    }
}
