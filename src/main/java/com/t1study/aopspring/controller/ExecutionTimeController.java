package com.t1study.aopspring.controller;


import com.t1study.aopspring.dto.AverageTimeResponse;
import com.t1study.aopspring.dto.ExecutionTimeResponse;
import com.t1study.aopspring.service.ExecutionTimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/exec-times")
public class ExecutionTimeController {

    private final ExecutionTimeService executionTimeService;

    @GetMapping()
    public ResponseEntity<List<ExecutionTimeResponse>> getTimes() {
        return ResponseEntity.status(HttpStatus.OK).body(executionTimeService.getTimes());
    }

    @GetMapping("/class-name")
    public ResponseEntity<List<ExecutionTimeResponse>> getTimesByClassName(@RequestParam(name = "className") String className) {
        return ResponseEntity.ok(executionTimeService.getTimesByClassName(className));
    }

    @GetMapping("/method-name")
    public ResponseEntity<List<ExecutionTimeResponse>> getTimesByMethodName(@RequestParam(name = "methodName") String methodName) {
        return ResponseEntity.ok(executionTimeService.getTimesByMethodName(methodName));
    }

    @GetMapping("/average/method-name")
    public ResponseEntity<AverageTimeResponse> getAverageTimeByMethodName(@RequestParam(name = "methodName") String methodName){
        return ResponseEntity.ok(executionTimeService.getAvgTimeByMethodName(methodName));
    }

    @GetMapping("/max/method-name")
    public ResponseEntity<ExecutionTimeResponse> getMaxTimeByMethodName(@RequestParam(name = "methodName") String methodName){
        return ResponseEntity.ok(executionTimeService.getMaxTimeByMethodName(methodName));
    }

    @GetMapping("/min/method-name")
    public ResponseEntity<ExecutionTimeResponse> getMinTimeByMethodName(@RequestParam(name = "methodName") String methodName){
        return ResponseEntity.ok(executionTimeService.getMinTimeByMethodName(methodName));
    }
}
