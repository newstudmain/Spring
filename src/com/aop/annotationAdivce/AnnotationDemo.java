package com.aop.annotationAdivce;

import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component("custom_annotationDemo")
public class AnnotationDemo {

	public AnnotationDemo() {
		System.out.println("使用注解生成bean... AnnotationDemo");
	}
	
	@Pointcut("execution(* com.aop.annotationAdivce.AnnotationDemo.testAnno1())")
	public void testAnno1() {
		System.out.println("AnnotationDemo@Pointcut...testAnno1()");
	}
}
