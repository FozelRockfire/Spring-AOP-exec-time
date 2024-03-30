package com.t1study.aopspring.service;

import com.t1study.aopspring.dto.ExecutionTimeResponse;
import com.t1study.aopspring.exception.NotFoundException;
import com.t1study.aopspring.mapper.ExecutionTimeMapper;
import com.t1study.aopspring.model.ExecutionTime;
import com.t1study.aopspring.repository.ExecutionTimeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExecutionTimeService {

    private final ExecutionTimeRepository executionTimeRepository;

    public void saveExecutionTime(ExecutionTime executionTime){
        executionTimeRepository.save(executionTime);
    }

    public List<ExecutionTimeResponse> getTimes() {
        return executionTimeRepository.findAll()
                .stream()
                .map(ExecutionTimeMapper.INSTANCE::toDTO)
                .toList();
    }

    public List<ExecutionTimeResponse> getTimesByClassName(String className) {
        List<ExecutionTime> executionTimes = executionTimeRepository.findAllByClassName(className)
                .orElseThrow(() -> new NotFoundException("Замеры времени выполнения искомого класса не найдены"));

        if (executionTimes.isEmpty()) {
            throw new NotFoundException("Замеры времени выполнения искомого класса не найдены");
        }

        return executionTimes.stream()
                .map(ExecutionTimeMapper.INSTANCE::toDTO)
                .toList();
    }

    public List<ExecutionTimeResponse> getTimesByMethodName(String methodName) {
        List<ExecutionTime> executionTimes = executionTimeRepository.findAllByMethodName(methodName)
                .orElseThrow(() -> new NotFoundException("Замеры времени выполнения искомого метода не найдены"));

        if (executionTimes.isEmpty()) {
            throw new NotFoundException("Замеры времени выполнения искомого метода не найдены");
        }

        return executionTimes.stream()
                .map(ExecutionTimeMapper.INSTANCE::toDTO)
                .toList();
    }
}
