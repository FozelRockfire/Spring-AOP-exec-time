package com.t1study.aopspring.aspect;

import com.t1study.aopspring.model.ExecutionTime;
import com.t1study.aopspring.service.ExecutionTimeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Aspect
@RequiredArgsConstructor
@Slf4j
public abstract class AbstractTrackTimeAspect {

    private final ExecutionTimeService executionTimeService;

    public abstract void trackPointcut();

    public abstract Object around(ProceedingJoinPoint proceedingJoinPoint);

    protected Object trackTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        long startTime = System.currentTimeMillis();

        String methodName = proceedingJoinPoint.getSignature().getName();
        Object[] methodArgs = proceedingJoinPoint.getArgs();

        log.info("Выполнение метода {} с аргументами {}", methodName, methodArgs);

        Object result = proceedingJoinPoint.proceed();

        long executionTimeValue = System.currentTimeMillis() - startTime;

        log.info("Метод {} выполнился за {} мс с результатом {}", methodName, executionTimeValue, methodArgs);

        ExecutionTime executionTime = ExecutionTime.builder()
                .className(proceedingJoinPoint.getSignature().getDeclaringType().getSimpleName())
                .methodName(methodName)
                .executionTime(executionTimeValue)
                .executionDate(LocalDateTime.now())
                .build();
        executionTimeService.saveExecutionTime(executionTime);

        return result;
    }

}
