package com.t1study.aopspring.service.ipml;

import com.t1study.aopspring.dto.AverageTimeResponse;
import com.t1study.aopspring.dto.ExecutionTimeResponse;
import com.t1study.aopspring.dto.RMSETimeResponse;
import com.t1study.aopspring.exception.NotFoundException;
import com.t1study.aopspring.mapper.AverageTimeMapper;
import com.t1study.aopspring.mapper.ExecutionTimeMapper;
import com.t1study.aopspring.mapper.RMSEMapper;
import com.t1study.aopspring.model.ExecutionTime;
import com.t1study.aopspring.repository.ExecutionTimeRepository;
import com.t1study.aopspring.service.ExecutionTimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExecutionTimeServiceImpl implements ExecutionTimeService {

    private final ExecutionTimeRepository executionTimeRepository;

    @Async
    @Transactional
    public void saveExecutionTime(ExecutionTime executionTime) {
        executionTimeRepository.save(executionTime);
    }

    public List<ExecutionTimeResponse> getTimes(String className, String methodName) {
        List<ExecutionTime> executionTimes;

        if (className == null && methodName == null) {
            executionTimes = executionTimeRepository.findAll();
        } else if (className != null && methodName != null) {
            executionTimes = executionTimeRepository.
                    findAllByClassNameAndMethodName(className, methodName);
        } else {
            executionTimes = executionTimeRepository.
                    findAllByClassNameOrMethodName(className, methodName);
        }

        if (executionTimes.isEmpty()) {
            throw new NotFoundException("Замеры времени выполнения искомых параметров не найдены");
        }

        return executionTimes.stream()
                .map(ExecutionTimeMapper.INSTANCE::toDTO)
                .toList();

    }

    public AverageTimeResponse getAvgTimeByMethodName(String methodName) {

        List<ExecutionTime> executionTimes = executionTimeRepository.findAllByMethodName(methodName);

        if (executionTimes.isEmpty()) {
            throw new NotFoundException("Замеры времени выполнения искомого метода не найдены");
        }

        double avgTime = getAVGTime(executionTimes);

        return AverageTimeMapper.INSTANCE.toDTO(executionTimes.get(0), avgTime);
    }

    public ExecutionTimeResponse getExtremumTimeByMethodName(String methodName, String extremum) {
        extremum = extremum.toLowerCase();

        return switch (extremum) {
            case "min" -> ExecutionTimeMapper.INSTANCE.toDTO(
                    executionTimeRepository.findFirstByMethodNameOrderByExecutionTimeAsc(methodName)
                            .orElseThrow(() -> new NotFoundException("Замеры времени выполнения искомого метода не найдены")));
            case "max" -> ExecutionTimeMapper.INSTANCE.toDTO(
                    executionTimeRepository.findFirstByMethodNameOrderByExecutionTimeDesc(methodName)
                            .orElseThrow(() -> new NotFoundException("Замеры времени выполнения искомого метода не найдены")));
            default -> throw new IllegalArgumentException("Неверный тип экстремума");
        };
    }

    public RMSETimeResponse getRMSEByMethodName(String methodName) {

        List<ExecutionTime> executionTimes = executionTimeRepository.findAllByMethodName(methodName);

        if (executionTimes.isEmpty()) {
            throw new NotFoundException("Замеры времени выполнения искомого метода не найдены");
        }

        double avgTime = getAVGTime(executionTimes);

        double sumOfSquaredDifferences = executionTimes.stream()
                .mapToDouble(executionTime -> Math.pow(executionTime.getExecutionTime() - avgTime, 2))
                .sum();

        double rmse = Math.sqrt(sumOfSquaredDifferences / executionTimes.size());

        return RMSEMapper.INSTANCE.toDTO(executionTimes.get(0), rmse);
    }

    public double getAVGTime(List<ExecutionTime> executionTimes) {

        return executionTimes.stream()
                .mapToLong(ExecutionTime::getExecutionTime)
                .average()
                .orElse(0);
    }

}
