package com.t1study.aopspring.service;

import com.t1study.aopspring.dto.AverageTimeResponse;
import com.t1study.aopspring.dto.ExecutionTimeResponse;
import com.t1study.aopspring.dto.RMSETimeResponse;
import com.t1study.aopspring.model.ExecutionTime;

import java.util.List;

public interface ExecutionTimeService {

    void saveExecutionTime(ExecutionTime executionTime);

    List<ExecutionTimeResponse> getTimes(String classname, String methodName);

    AverageTimeResponse getAvgTimeByMethodName(String methodName);

    ExecutionTimeResponse getExtremumTimeByMethodName(String methodName, String extremum);

    RMSETimeResponse getRMSEByMethodName(String methodName);
}

