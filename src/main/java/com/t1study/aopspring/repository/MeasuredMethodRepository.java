package com.t1study.aopspring.repository;

import com.t1study.aopspring.model.MeasuredClass;
import com.t1study.aopspring.model.MeasuredMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeasuredMethodRepository extends JpaRepository<MeasuredMethod, Long> {

    MeasuredMethod findMeasuredMethodByMethodNameAndMeasuredClass(String methodName, MeasuredClass measuredClass);
}
