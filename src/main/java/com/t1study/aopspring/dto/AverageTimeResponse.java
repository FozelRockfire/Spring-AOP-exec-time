package com.t1study.aopspring.dto;

import lombok.Builder;

@Builder
public record AverageTimeResponse (

        String className,
        String methodName,
        Long avgTime
) {
}
