package com.t1study.aopspring.repository;


import com.t1study.aopspring.model.ExecutionTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExecutionTimeRepository extends JpaRepository<ExecutionTime, Long>{

    Optional<List<ExecutionTime>> findAllByClassName(String classname);

    Optional<List<ExecutionTime>> findAllByMethodName(String classname);
}