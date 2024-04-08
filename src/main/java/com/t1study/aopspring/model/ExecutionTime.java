package com.t1study.aopspring.model;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "t_execution_times")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExecutionTime {

    @Id
    @Column(name = "execution_time_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "execution_time")
    private long executionTime;

    @Column(name = "execution_date")
    private LocalDateTime executionDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "method_id", referencedColumnName = "method_id")
    private MeasuredMethod measuredMethod;
}
