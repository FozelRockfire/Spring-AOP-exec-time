package com.t1study.aopspring.service.ipml;


import com.t1study.aopspring.annotation.TrackAsyncTime;
import com.t1study.aopspring.annotation.TrackTime;
import com.t1study.aopspring.service.AnnotationTestService;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class AnnotationTestServiceImpl implements AnnotationTestService {


    @TrackTime
    public void sleepRandomTimeUpTo3Seconds(){
        sleepRandom(3000);
    }

    @TrackAsyncTime
    public void asyncSleepRandomTimeUpTo3Seconds(){
        sleepRandom(3000);
    }

    @TrackTime
    public void sleepRandomTimeUpToVarSeconds(Integer bound) {
        sleepRandom(bound);
    }

    @SneakyThrows
    private void sleepRandom(Integer bound){
        var random = new Random();
        Thread.sleep(random.nextInt(bound));
    }
}
