package com.transaction.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.aop.annotationAdivce.AnnotationDemo;

public class TransactionTest {

	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		//AnnotationTest annoTest = ac.getBean("annotest",AnnotationTest.class);
		
		String[] names = ac.getBeanDefinitionNames();
		for (String string : names) {
			System.out.println(string);
		}
		
		System.out.println("------");
		
		AnnotationDemo annodemo = ac.getBean("custom_annotationDemo",AnnotationDemo.class);
		annodemo.testAnno1();
	}
}
