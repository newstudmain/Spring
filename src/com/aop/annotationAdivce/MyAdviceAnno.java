package com.aop.annotationAdivce;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MyAdviceAnno{

	@Before("com.aop.annotationAdivce.AnnotationDemo.testAnno1()")
	public void myBefore() {
		System.out.println("MyAdviceAnno...myBefore()");
	}
	
	@After("com.aop.annotationAdivce.AnnotationDemo.testAnno1()")
	public void myAfter() {
		System.out.println("MyAdviceAnno...myAfter()");
	}
	
	@AfterThrowing("com.aop.annotationAdivce.AnnotationDemo.testAnno1()")
	public void myThrow() {
		System.out.println("MyAdviceAnno...myThrow()");
	}
	
	@Around("com.aop.annotationAdivce.AnnotationDemo.testAnno1()")
	public Object myAround(ProceedingJoinPoint p) throws Throwable {
		
		//System.out.println("======MyAdviceAnno...myAround=====");
		
		System.out.println("环绕-前置...");
		//myBefore();
		Object result = p.proceed();
		//myAfter();
		System.out.println("环绕-后置...");
		
		//System.out.println("======MyAdviceAnno...myAround=====");
		
		return result;
	}

}
