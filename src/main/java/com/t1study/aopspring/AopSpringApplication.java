package com.t1study.aopspring;

import com.t1study.aopspring.service.AnnotationTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class AopSpringApplication {

	@Autowired
	AnnotationTestService annotationTestService;

	public static void main(String[] args) {
		SpringApplication.run(AopSpringApplication.class, args);
	}

//	@EventListener(ApplicationReadyEvent.class)
//	public void onReady() {
//		annotationTestService.sleepRandomTimeUpTo3Seconds();
//	}
}
