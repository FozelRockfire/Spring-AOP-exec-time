package com.t1study.aopspring.aspect;


import com.t1study.aopspring.service.ipml.ExecutionTimeServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Aspect
@Component
@Slf4j
public class TrackAsyncTimeAspect extends AbstractTrackTimeAspect {

    public TrackAsyncTimeAspect(ExecutionTimeServiceImpl executionTimeServiceImpl) {
        super(executionTimeServiceImpl);
    }

    @Override
    @Pointcut("@annotation(com.t1study.aopspring.annotation.TrackAsyncTime)")
    public void trackPointcut() {
    }

    @Around("trackPointcut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) {

        try {
            return CompletableFuture.supplyAsync(() -> {
                try {
                    log.info("Метод с @TrackAsyncTime");
                    return trackTime(proceedingJoinPoint);
                } catch (Throwable e) {
                    log.error("AsyncTrackTime error:", e);
                    return null;
                }
            }).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

//        return CompletableFuture.runAsync(() -> {
//            try {
//                log.info("Метод с @TrackAsyncTime");
//                trackTime(proceedingJoinPoint);
//            } catch (Throwable e) {
//                log.error("AsyncTrackTime error:", e);
//            }
//        });
    }
}
