package com.t1study.aopspring.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record RMSETimeResponse (

        @Schema(description = "Имя класса",
                example = "AnnotationTestServiceImpl")
        String className,

        @Schema(description = "Имя метода",
                example = "sleepRandomTimeUpTo3Seconds")
        String methodName,

        @Schema(description = "Среднеквадратичная ошибка",
                example = "100")
        double RMSE
) {
}
