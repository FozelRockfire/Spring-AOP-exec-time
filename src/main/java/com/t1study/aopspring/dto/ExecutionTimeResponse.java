package com.t1study.aopspring.dto;


public record ExecutionTimeResponse(

        String className,
        String methodName,
        Long executionTime
) {
}
