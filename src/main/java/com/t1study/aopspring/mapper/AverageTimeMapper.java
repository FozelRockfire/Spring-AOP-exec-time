package com.t1study.aopspring.mapper;

import com.t1study.aopspring.dto.AverageTimeResponse;
import com.t1study.aopspring.model.ExecutionTime;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AverageTimeMapper extends Mappable<ExecutionTime, AverageTimeResponse>{

    AverageTimeMapper INSTANCE = Mappers.getMapper(AverageTimeMapper.class);

    default AverageTimeResponse toDTO(ExecutionTime executionTime, long avgTime) {

        return AverageTimeResponse.builder()
                .avgTime(avgTime)
                .methodName(executionTime.getMethodName())
                .className(executionTime.getClassName())
                .build();
    }
}
