package com.t1study.aopspring.api;

import com.t1study.aopspring.dto.AverageTimeResponse;
import com.t1study.aopspring.dto.ExecutionTimeResponse;
import com.t1study.aopspring.dto.RMSETimeResponse;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Tag(name = "ExecutionTimeApi", description = "ExecutionTimeApi")
public interface ExecutionTimeApi {

    @Operation(
            description = "Позволяет получить временные данные выполнения методов"
    )
    @GetMapping()
    @ApiResponses(value = {
            @ApiResponse(
                    code = 200,
                    message = "Данные получены",
                    response = ExecutionTimeResponse.class),
            @ApiResponse(
                    code = 400,
                    message = "В случае нарушения контракта",
                    response = ErrorResponse.class),
            @ApiResponse(
                    code = 500,
                    message = "В случае внутренних ошибок",
                    response = ErrorResponse.class)
    })
    ResponseEntity<List<ExecutionTimeResponse>> getTimes(
            @Parameter(name = "className", description = "Имя класса", example = "AnnotationTestServiceImpl")
            @RequestParam(name = "className", required = false) String className,
            @Parameter(name = "methodName", description = "Имя метода", example = "sleepRandomTimeUpTo3Seconds")
            @RequestParam(name = "methodName", required = false) String methodName);


    @Operation(
            description = "Получение среднего времени выполнения метода"
    )
    @GetMapping("/average")
    @ApiResponses(value = {
            @ApiResponse(
                    code = 200,
                    message = "Данные получены",
                    response = AverageTimeResponse.class),
            @ApiResponse(
                    code = 400,
                    message = "В случае нарушения контракта",
                    response = ErrorResponse.class),
            @ApiResponse(
                    code = 500,
                    message = "В случае внутренних ошибок",
                    response = ErrorResponse.class)
    })
    ResponseEntity<AverageTimeResponse> getAverageTimeByMethodName(
            @Parameter(name = "methodName", description = "Имя метода", example = "sleepRandomTimeUpTo3Seconds")
            @RequestParam(name = "methodName") String methodName);


    @Operation(
            description = "Получение экстремумов времени выполнения метода"
    )
    @GetMapping("/extremum")
    @ApiResponses(value = {
            @ApiResponse(
                    code = 200,
                    message = "Данные получены",
                    response = ExecutionTimeResponse.class),
            @ApiResponse(
                    code = 400,
                    message = "В случае нарушения контракта",
                    response = ErrorResponse.class),
            @ApiResponse(
                    code = 500,
                    message = "В случае внутренних ошибок",
                    response = ErrorResponse.class)
    })
    ResponseEntity<ExecutionTimeResponse> getExtremumTimeByMethodName(
            @Parameter(name = "methodName", description = "Имя метода", example = "sleepRandomTimeUpTo3Seconds")
            @RequestParam(name = "methodName") String methodName,
            @Parameter(name = "extremum", description = "Тип экстремума", example = "max")
            @RequestParam(name = "extremum") String extremum);


    @Operation(
            description = "Получение среднеквадратичного отклонения времени выполнения метода"
    )
    @GetMapping("/rmse")
    @ApiResponses(value = {
            @ApiResponse(
                    code = 200,
                    message = "Данные получены",
                    response = RMSETimeResponse.class),
            @ApiResponse(
                    code = 400,
                    message = "В случае нарушения контракта",
                    response = ErrorResponse.class),
            @ApiResponse(
                    code = 500,
                    message = "В случае внутренних ошибок",
                    response = ErrorResponse.class)
    })
    ResponseEntity<RMSETimeResponse> getRMSEByMethodName(
            @Parameter(name = "methodName", description = "Имя метода", example = "sleepRandomTimeUpTo3Seconds")
            @RequestParam(name = "methodName") String methodName);
}
