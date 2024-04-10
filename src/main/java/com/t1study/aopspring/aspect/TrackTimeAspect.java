package com.t1study.aopspring.aspect;

import com.t1study.aopspring.service.ExecutionTimeService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class TrackTimeAspect extends AbstractTrackTimeAspect {

    public TrackTimeAspect(ExecutionTimeService executionTimeService) {
        super(executionTimeService);
    }

    @Override
    @Pointcut("@annotation(com.t1study.aopspring.annotation.TrackTime)")
    public void trackPointcut() {
    }

    @Around("trackPointcut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) {
        try {
            return trackTime(proceedingJoinPoint, "TrackTime");
        } catch (Throwable e) {
            log.error("TrackTime error:", e);
            throw new RuntimeException(e);
        }
    }
}
