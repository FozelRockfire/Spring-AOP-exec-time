package com.t1study.aopspring.service;

import com.t1study.aopspring.dto.AverageTimeResponse;
import com.t1study.aopspring.dto.ExecutionTimeResponse;
import com.t1study.aopspring.model.ExecutionTime;

import java.util.List;

public interface ExecutionTimeService {

    void saveExecutionTime(ExecutionTime executionTime);

    List<ExecutionTimeResponse> getTimes();

    List<ExecutionTimeResponse> getTimesByClassName(String className);

    List<ExecutionTimeResponse> getTimesByMethodName(String methodName);

    AverageTimeResponse getAvgTimeByMethodName(String methodName);

    ExecutionTimeResponse getMaxTimeByMethodName(String methodName);

    ExecutionTimeResponse getMinTimeByMethodName(String methodName);
}

