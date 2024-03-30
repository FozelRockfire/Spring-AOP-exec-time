package com.t1study.aopspring.mapper;

public interface Mappable <E, D>{

    D toDTO(E entity);

    E toEntity(D dto);
}
