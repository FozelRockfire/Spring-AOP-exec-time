package com.t1study.aopspring.service;


import com.t1study.aopspring.annotation.TrackTime;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class AnnotationTestService {

    @SneakyThrows
    @TrackTime
    public void sleepRandomTimeUpTo3Seconds(){
        var random = new Random();
        System.out.println("буду ждать");
        Thread.sleep(random.nextInt(3000));
        System.out.println("подождал");
    }
}
