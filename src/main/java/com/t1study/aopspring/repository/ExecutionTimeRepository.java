package com.t1study.aopspring.repository;


import com.t1study.aopspring.model.ExecutionTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExecutionTimeRepository extends JpaRepository<ExecutionTime, Long>{

    List<ExecutionTime> findAllByClassName(String className);

    List<ExecutionTime> findAllByMethodName(String methodName);

    List<ExecutionTime> findAllByClassNameOrMethodName(String className, String methodName);

    List<ExecutionTime> findAllByClassNameAndMethodName(String className, String methodName);

    Optional<ExecutionTime> findFirstByMethodNameOrderByExecutionTimeDesc(String methodName);

    Optional<ExecutionTime> findFirstByMethodNameOrderByExecutionTimeAsc(String methodName);
}
