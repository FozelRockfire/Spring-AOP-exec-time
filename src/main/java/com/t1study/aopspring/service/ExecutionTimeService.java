package com.t1study.aopspring.service;

import com.t1study.aopspring.dto.AverageTimeResponse;
import com.t1study.aopspring.dto.ExecutionTimeResponse;
import com.t1study.aopspring.dto.RMSETimeResponse;

import java.util.List;

public interface ExecutionTimeService {

    void saveExecutionTime(Long executionTimeValue, String methodName, String className);

    List<ExecutionTimeResponse> getTimes(String classname, String methodName);

    AverageTimeResponse getAvgTimeByMethodName(String methodName);

    ExecutionTimeResponse getExtremumTimeByMethodName(String methodName, String extremum);

    RMSETimeResponse getRMSEByMethodName(String methodName);
}

