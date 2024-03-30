package com.t1study.aopspring.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "t_execution_time")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExecutionTime {

    @Id
    @Column(name = "t_execution_time_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "t_class_name")
    private String className;

    @Column(name = "t_method_name")
    private String methodName;

    @Column(name = "t_execution_time")
    private long executionTime;
}
