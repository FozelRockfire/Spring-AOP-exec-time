package com.t1study.aopspring.repository;


import com.t1study.aopspring.model.ExecutionTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExecutionTimeRepository extends JpaRepository<ExecutionTime, Long>{

    @Query("""
        SELECT et FROM ExecutionTime et
        JOIN et.measuredMethod m
        JOIN m.measuredClass c
        WHERE m.methodName = :methodName
        """)
    List<ExecutionTime> findAllByMethodName(String methodName);

    @Query("""
        SELECT et FROM ExecutionTime et
        JOIN et.measuredMethod m
        JOIN m.measuredClass c
        WHERE c.className = :className OR m.methodName = :methodName
        """)
    List<ExecutionTime> findAllByClassNameOrMethodName(String className, String methodName);

    @Query("""
        SELECT et FROM ExecutionTime et
        JOIN et.measuredMethod m
        JOIN m.measuredClass c
        WHERE c.className = :className AND m.methodName = :methodName
        """)
    List<ExecutionTime> findAllByClassNameAndMethodName(String className, String methodName);

    @Query("""
        SELECT et FROM ExecutionTime et
        JOIN et.measuredMethod m
        JOIN m.measuredClass c
        WHERE m.methodName = :methodName
        ORDER BY et.executionTime DESC
        LIMIT 1
        """)
    Optional<ExecutionTime> findMaxMethodValue(String methodName);

    @Query("""
        SELECT et FROM ExecutionTime et
        JOIN et.measuredMethod m
        JOIN m.measuredClass c
        WHERE m.methodName = :methodName
        ORDER BY et.executionTime ASC
        LIMIT 1
        """)
    Optional<ExecutionTime> findMinMethodValue(String methodName);
}
