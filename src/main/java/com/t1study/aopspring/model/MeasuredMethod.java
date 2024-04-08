package com.t1study.aopspring.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "t_methods")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MeasuredMethod {

    @Id
    @Column(name = "method_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "method_name")
    private String methodName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_id", referencedColumnName = "class_id")
    private MeasuredClass measuredClass;

    @OneToMany(mappedBy = "measuredMethod", cascade = CascadeType.ALL)
    private Set<ExecutionTime> executionTimes = new HashSet<>();
}
