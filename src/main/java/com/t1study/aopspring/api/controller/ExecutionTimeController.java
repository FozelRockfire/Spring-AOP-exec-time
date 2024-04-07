package com.t1study.aopspring.api.controller;


import com.t1study.aopspring.api.ExecutionTimeApi;
import com.t1study.aopspring.dto.AverageTimeResponse;
import com.t1study.aopspring.dto.ExecutionTimeResponse;
import com.t1study.aopspring.dto.RMSETimeResponse;
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
public class ExecutionTimeController implements ExecutionTimeApi {

    private final ExecutionTimeService executionTimeService;

    @GetMapping()
    public ResponseEntity<List<ExecutionTimeResponse>> getTimes(@RequestParam(name = "className", required = false) String className,
                                                                @RequestParam(name = "methodName", required = false) String methodName) {
        return ResponseEntity.status(HttpStatus.OK).body(executionTimeService.getTimes(className, methodName));
    }

    @GetMapping("/average")
    public ResponseEntity<AverageTimeResponse> getAverageTimeByMethodName(@RequestParam(name = "methodName") String methodName) {
        return ResponseEntity.ok(executionTimeService.getAvgTimeByMethodName(methodName));
    }

    @GetMapping("/extremum")
    public ResponseEntity<ExecutionTimeResponse> getExtremumTimeByMethodName(@RequestParam(name = "methodName") String methodName,
                                                                             @RequestParam(name = "extremum") String extremum) {
        return ResponseEntity.ok(executionTimeService.getExtremumTimeByMethodName(methodName, extremum));
    }

    @GetMapping("/rmse")
    public ResponseEntity<RMSETimeResponse> getRMSEByMethodName(@RequestParam(name = "methodName") String methodName) {
        return ResponseEntity.ok(executionTimeService.getRMSEByMethodName(methodName));
    }

}
