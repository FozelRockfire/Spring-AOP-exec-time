package com.t1study.aopspring.aspect;

import com.t1study.aopspring.annotation.TrackTime;
import com.t1study.aopspring.model.ExecutionTime;
import com.t1study.aopspring.service.ExecutionTimeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
@RequiredArgsConstructor
public class TrackTimeAspect {

    private final ExecutionTimeService executionTimeService;

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

        long executionTimeValue = System.currentTimeMillis() - startTime;

        log.info("Метод {} выполнился за {} мс с результатом {}", methodName, executionTimeValue, methodArgs);

        ExecutionTime executionTime = ExecutionTime.builder()
                .className(proceedingJoinPoint.getSignature().getDeclaringType().getSimpleName())
                .methodName(proceedingJoinPoint.getSignature().getName())
                .executionTime(executionTimeValue)
                .build();
        executionTimeService.saveExecutionTime(executionTime);

        return result;
    }
}
