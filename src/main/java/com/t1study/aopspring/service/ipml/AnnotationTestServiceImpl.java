package com.t1study.aopspring.service.ipml;


import com.t1study.aopspring.annotation.TrackAsyncTime;
import com.t1study.aopspring.annotation.TrackTime;
import com.t1study.aopspring.service.AnnotationTestService;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class AnnotationTestServiceImpl implements AnnotationTestService {

    @SneakyThrows
    @TrackTime
    public void sleepRandomTimeUpTo3Seconds(){
        var random = new Random();
        Thread.sleep(random.nextInt(3000));
    }

    @SneakyThrows
    @TrackAsyncTime
    public void asyncSleepRandomTimeUpTo3Seconds(){
        var random = new Random();
        Thread.sleep(random.nextInt(3000));
    }
}
