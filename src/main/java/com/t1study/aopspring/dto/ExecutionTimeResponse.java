package com.t1study.aopspring.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ExecutionTimeResponse(

        @Schema(description = "Имя класса",
                example = "AnnotationTestServiceImpl")
        String className,

        @Schema(description = "Имя метода",
                example = "sleepRandomTimeUpTo3Seconds")
        String methodName,

        @Schema(description = "Время выполнения",
                example = "2120")
        long executionTime,

        @Schema(description = "Дата выполнения",
                example = "2024-04-07T14:18:10.689618")
        LocalDateTime executionDate
) {
}
