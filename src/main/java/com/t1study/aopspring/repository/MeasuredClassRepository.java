package com.t1study.aopspring.repository;

import com.t1study.aopspring.model.MeasuredClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeasuredClassRepository extends JpaRepository<MeasuredClass, Long> {

    MeasuredClass findMeasuredClassByClassName(String className);

}
