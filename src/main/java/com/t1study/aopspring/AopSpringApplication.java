package com.t1study.aopspring;

import com.t1study.aopspring.service.AnnotationTestService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@RequiredArgsConstructor
public class AopSpringApplication {

    private final AnnotationTestService annotationTestService;

    public static void main(String[] args) {
        SpringApplication.run(AopSpringApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void onReady() {
        for (int i = 0; i < 2; i++) {
            annotationTestService.sleepRandomTimeUpTo3Seconds();
            annotationTestService.asyncSleepRandomTimeUpTo3Seconds();
            annotationTestService.sleepRandomTimeUpToVarSeconds(2000);
        }
    }
}
