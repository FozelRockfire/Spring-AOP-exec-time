package com.t1study.aopspring.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ExecutionTimeResponse(

        String className,
        String methodName,
        Long executionTime,
        LocalDateTime executionDate
) {
}
