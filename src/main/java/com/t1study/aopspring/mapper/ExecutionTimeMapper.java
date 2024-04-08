package com.t1study.aopspring.mapper;

import com.t1study.aopspring.dto.ExecutionTimeResponse;
import com.t1study.aopspring.model.ExecutionTime;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ExecutionTimeMapper extends Mappable<ExecutionTime, ExecutionTimeResponse>{

    ExecutionTimeMapper INSTANCE = Mappers.getMapper(ExecutionTimeMapper.class);

    default ExecutionTimeResponse toDTO(ExecutionTime executionTime){
        return ExecutionTimeResponse.builder()
                .className(executionTime.getMeasuredMethod().getMeasuredClass().getClassName())
                .methodName(executionTime.getMeasuredMethod().getMethodName())
                .executionTime(executionTime.getExecutionTime())
                .executionDate(executionTime.getExecutionDate())
                .build();
    }
}
