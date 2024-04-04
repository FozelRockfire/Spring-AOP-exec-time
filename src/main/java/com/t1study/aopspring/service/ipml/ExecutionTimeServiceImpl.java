package com.t1study.aopspring.service.ipml;

import com.t1study.aopspring.dto.AverageTimeResponse;
import com.t1study.aopspring.dto.ExecutionTimeResponse;
import com.t1study.aopspring.exception.NotFoundException;
import com.t1study.aopspring.mapper.AverageTimeMapper;
import com.t1study.aopspring.mapper.ExecutionTimeMapper;
import com.t1study.aopspring.model.ExecutionTime;
import com.t1study.aopspring.repository.ExecutionTimeRepository;
import com.t1study.aopspring.service.ExecutionTimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class ExecutionTimeServiceImpl implements ExecutionTimeService {

    private final ExecutionTimeRepository executionTimeRepository;

    @Async
    public void saveExecutionTime(ExecutionTime executionTime) {
        executionTimeRepository.save(executionTime);
    }

    public List<ExecutionTimeResponse> getTimes() {
        return executionTimeRepository.findAll()
                .stream()
                .map(ExecutionTimeMapper.INSTANCE::toDTO)
                .toList();
    }

    public List<ExecutionTimeResponse> getTimesByClassName(String className) {
        List<ExecutionTime> executionTimes = this.checkExecutionTimeListRepoAnswer(executionTimeRepository::findAllByClassName,
                className, "Замеры времени выполнения искомого класса не найдены");

        return executionTimes.stream()
                .map(ExecutionTimeMapper.INSTANCE::toDTO)
                .toList();
    }

    public List<ExecutionTimeResponse> getTimesByMethodName(String methodName) {
        List<ExecutionTime> executionTimes = this.checkExecutionTimeListRepoAnswer(executionTimeRepository::findAllByMethodName,
                methodName, "Замеры времени выполнения искомого метода не найдены");

        return executionTimes.stream()
                .map(ExecutionTimeMapper.INSTANCE::toDTO)
                .toList();
    }

    public AverageTimeResponse getAvgTimeByMethodName(String methodName) {
        List<ExecutionTime> executionTimes = this.checkExecutionTimeListRepoAnswer(executionTimeRepository::findAllByMethodName,
                methodName, "Замеры времени выполнения искомого метода не найдены");

        long totalTime = executionTimes.stream()
                .mapToLong(ExecutionTime::getExecutionTime)
                .sum();

        return AverageTimeMapper.INSTANCE.toDTO(executionTimes.get(0), totalTime / executionTimes.size());

    }

    public ExecutionTimeResponse getMaxTimeByMethodName(String methodName) {
        return ExecutionTimeMapper.INSTANCE.toDTO(
                executionTimeRepository.findFirstByMethodNameOrderByExecutionTimeDesc(methodName)
                        .orElseThrow(() -> new NotFoundException("Замеры времени выполнения искомого метода не найдены")));
    }

    public ExecutionTimeResponse getMinTimeByMethodName(String methodName) {
        return ExecutionTimeMapper.INSTANCE.toDTO(
                executionTimeRepository.findFirstByMethodNameOrderByExecutionTimeAsc(methodName)
                        .orElseThrow(() -> new NotFoundException("Замеры времени выполнения искомого метода не найдены")));
    }

    private List<ExecutionTime> checkExecutionTimeListRepoAnswer(Function<String, Optional<List<ExecutionTime>>> findByMethodFunction, String methodParameter, String errorMessage) {
        List<ExecutionTime> executionTimes = findByMethodFunction.apply(methodParameter)
                .orElseThrow(() -> new NotFoundException(errorMessage));

        if (executionTimes.isEmpty()) {
            throw new NotFoundException(errorMessage);
        }

        return executionTimes;
    }

}
