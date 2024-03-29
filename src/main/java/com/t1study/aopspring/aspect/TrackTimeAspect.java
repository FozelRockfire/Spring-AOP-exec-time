package com.t1study.aopspring.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class TrackTimeAspect {

    @Pointcut("@annotation(com.t1study.aopspring.annotation.TrackTime)")
    public void trackTimePointcut() {
    }

    @Around("trackTimePointcut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        long startTime = System.currentTimeMillis();

        String methodName = proceedingJoinPoint.getSignature().getName();
        Object[] methodArgs = proceedingJoinPoint.getArgs();

        log.info("Выполнение метода {} с аргументами {}", methodName, methodArgs);

        Object result = proceedingJoinPoint.proceed();

        long endTime = System.currentTimeMillis();

        log.info("Метод {} выполнился за {} мс с результатом {}", methodName, endTime - startTime, methodArgs);

        return result;
    }
}
