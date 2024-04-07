package com.t1study.aopspring.mapper;

import com.t1study.aopspring.dto.RMSETimeResponse;
import com.t1study.aopspring.model.ExecutionTime;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RMSEMapper extends Mappable<ExecutionTime, RMSETimeResponse> {

    RMSEMapper INSTANCE = Mappers.getMapper(RMSEMapper.class);

    default RMSETimeResponse toDTO(ExecutionTime executionTime, double rmse) {

        return RMSETimeResponse.builder()
                .RMSE(rmse)
                .methodName(executionTime.getMethodName())
                .className(executionTime.getClassName())
                .build();
    }
}
