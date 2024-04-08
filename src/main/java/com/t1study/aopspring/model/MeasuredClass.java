package com.t1study.aopspring.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "t_classes")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MeasuredClass {

    @Id
    @Column(name = "class_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "class_name")
    private String className;

    @OneToMany(mappedBy = "measuredClass", cascade = CascadeType.ALL)
    private Set<MeasuredMethod> measuredMethods = new HashSet<>();

}
